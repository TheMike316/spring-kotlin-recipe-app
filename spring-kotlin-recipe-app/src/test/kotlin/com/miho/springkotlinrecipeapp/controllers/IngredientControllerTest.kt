package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockitoWhen
import org.mockito.Matchers.anyLong
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.services.IngredientService


class IngredientControllerTest {
	
	private lateinit var controller: IngredientController
	
	private lateinit var mockMvc: MockMvc	
	
	@Mock
	private lateinit var recipeService: RecipeService
	
	@Mock
	private lateinit var ingredientService: IngredientService
	
	@Before
	fun startUp(){
		MockitoAnnotations.initMocks(this)
		
		controller = IngredientController(recipeService, ingredientService)
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
	}
	
	@Test
	fun testListIngredients(){
//		given
		val recipeCommand = RecipeCommand()
		mockitoWhen(recipeService.findById(anyLong())).thenReturn(recipeCommand)
		
//		when
		mockMvc.perform(get("/recipe/1/ingredients"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(model().attributeExists("recipe"))
		
		verify(recipeService, times(1)).findById(anyLong())
		
	}
	
	@Test
	fun testShowIngredient(){
//		given
		val ingredientCommand = IngredientCommand(id = 2L)
		
//		when
		mockitoWhen(ingredientService.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredientCommand)
		
//		then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"))
	}
}