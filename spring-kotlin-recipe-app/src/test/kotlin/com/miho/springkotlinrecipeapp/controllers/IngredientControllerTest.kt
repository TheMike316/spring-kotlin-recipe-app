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
import com.miho.springkotlinrecipeapp.services.UnitOfMeasureService
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand


class IngredientControllerTest {
	
	private lateinit var controller: IngredientController
	
	private lateinit var mockMvc: MockMvc	
	
	@Mock
	private lateinit var recipeService: RecipeService
	
	@Mock
	private lateinit var ingredientService: IngredientService
	
	@Mock
	private lateinit var uomService: UnitOfMeasureService
	
	@Before
	fun startUp(){
		MockitoAnnotations.initMocks(this)
		
		controller = IngredientController(recipeService, ingredientService, uomService)
	
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
		mockitoWhen(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand)
		
//		then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"))
	}
	
	@Test
	fun testGetUpdateForm(){
//		given
		val ingredientCommand = IngredientCommand(id = 3)
		val uoms = setOf(UnitOfMeasureCommand(id = 1), UnitOfMeasureCommand(id = 2), UnitOfMeasureCommand(id = 3))
		
//		when
		mockitoWhen(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand)
		mockitoWhen(uomService.listAllUoms()).thenReturn(uoms)
		
//		then
		mockMvc.perform(get("/recipe/2/ingredient/3/update"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/ingredient/ingredientform"))
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(model().attributeExists("uomList"))

		
	}
}