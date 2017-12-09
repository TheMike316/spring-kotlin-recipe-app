package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.domain.Difficulty
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockitoWhen
import org.mockito.Matchers

class RecipeControllerTest {
	
	private lateinit var controller: RecipeController
	
	private lateinit var mockMvc: MockMvc
	
	
	
	
	@Mock
	private var service: RecipeService? = null
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		controller = RecipeController(service!!)
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
		
	}
	
	@Test
	fun testGetRecipe(){
		
		val recipe = RecipeCommand(id = 1L, description = "", prepTime = 0, cookTime = 0, servings = 0,
				                   source = "", url = "", directions = "", notes = NotesCommand(1L, ""), ingredients = emptySet<IngredientCommand>().toMutableSet(),
				                   difficulty = Difficulty.EASY, categories = emptySet<CategoryCommand>().toMutableSet())
		
		
		
		mockitoWhen(service?.findById(anyLong()))?.thenReturn(recipe)
		
		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk).andExpect(view().name("recipe/show"))
		
		
	}
	
	@Test
	fun testGetNewRecipeForm(){
		
		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"))
		
	}
	
	@Test
	fun testPostNewRecipeForm(){
		
		val command = RecipeCommand(id = 2L)
		
		mockitoWhen(service?.saveRecipe(command)).thenReturn(command)
		
		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "2")
				.param("description", ""))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show"))
		
		
	}
	
	@Test
	fun testGetUpdateView(){
		
		val command = RecipeCommand(id = 2L)
		
		mockitoWhen(service?.findById(anyLong())).thenReturn(command)
		
		mockMvc.perform(get("/recipe/2/update"))
		   		.andExpect(status().isOk)
		   		.andExpect(view().name("recipe/recipeform"))
		   		.andExpect(model().attributeExists("recipe"))
		
	}
}