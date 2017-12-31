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
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import com.miho.springkotlinrecipeapp.exceptions.NotFoundException

class RecipeControllerTest {

	private lateinit var controller: RecipeController

	private lateinit var mockMvc: MockMvc

	@Mock
	private var service: RecipeService? = null

	@Before
	fun setUp() {

		MockitoAnnotations.initMocks(this)

		controller = RecipeController(service!!)

		mockMvc = MockMvcBuilders.standaloneSetup(controller)
								.setControllerAdvice(ControllerExceptionHandler()).build()

	}

	@Test
	fun testGetRecipe() {

		val recipe = RecipeCommand(id = 1L, description = "", prepTime = 0, cookTime = 0, servings = 0,
				source = "", url = "", directions = "", notes = NotesCommand(1L, ""), ingredients = emptySet<IngredientCommand>().toMutableSet(),
				difficulty = Difficulty.EASY, categories = emptySet<CategoryCommand>().toMutableSet())



		mockitoWhen(service?.findById(anyLong()))?.thenReturn(recipe)

		mockMvc.perform(get("/recipe/1/show")).andExpect(status().isOk).andExpect(view().name("recipe/show"))


	}

	@Test
	fun testGetRecipeNotFound() {

		mockitoWhen(service?.findById(anyLong())).thenThrow(NotFoundException::class.java)

		mockMvc.perform(get("/recipe/4/show"))
				.andExpect(status().isNotFound)
				.andExpect(view().name("404error"))
	}

	@Test
	fun testBadRequest() {		

		mockMvc.perform(get("/recipe/asdf/show"))
				.andExpect(status().isBadRequest)
				.andExpect(view().name("400error"))
	}


	@Test
	fun testGetNewRecipeForm() {

		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"))

	}

	@Test
	fun testPostNewRecipeForm() {

		val command = RecipeCommand(id = 2L)

		mockitoWhen(service?.saveRecipe(any<RecipeCommand>())).thenReturn(command)

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", command.id.toString())
				.param("description", "assduff")
				.param("prepTime", "4")
				.param("cookTime", "30")
				.param("servings", "4")
				.param("directions", "asdf"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/${command.id}/show"))


	}
	
	@Test
	fun testPostNewRecipeFormFailValidation() {

		val command = RecipeCommand(id = 2L)

		mockitoWhen(service?.saveRecipe(any<RecipeCommand>())).thenReturn(command)

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", command.id.toString())
				.param("description", ""))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/recipeform"))


	}

	@Test
	fun testGetUpdateView() {

		val command = RecipeCommand(id = 2L)

		mockitoWhen(service?.findById(anyLong())).thenReturn(command)

		mockMvc.perform(get("/recipe/2/update"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"))

	}

	@Test
	fun testDeletAction() {

		mockMvc.perform(get("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection)
				.andExpect(view().name("redirect:/"))

		verify(service, times(1))?.deleteById(anyLong())
	}
}

