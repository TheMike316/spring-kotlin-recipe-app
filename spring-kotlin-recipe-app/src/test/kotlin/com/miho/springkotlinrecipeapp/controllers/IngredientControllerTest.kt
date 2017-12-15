package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.services.IngredientService
import com.miho.springkotlinrecipeapp.services.RecipeService
import com.miho.springkotlinrecipeapp.services.UnitOfMeasureService
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.any
import org.mockito.Matchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal
import org.mockito.Mockito.`when` as mockitoWhen


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
	
	@Test
	fun testPostUpdateIngredient(){
		
//		given
		val uom = UnitOfMeasureCommand(id = 2, unit = "Grams")
		val ingredientCommand = IngredientCommand(id = 4, description = "molten cheese", unitOfMeasure = uom, amount = BigDecimal(100))
		val recipeId = 1
		
//		when
		mockitoWhen(ingredientService.saveIngredient(any())).thenReturn(ingredientCommand)
		
//		then
		mockMvc.perform(post("/recipe/$recipeId/ingredient")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("id", ingredientCommand.id.toString())
						.param("description", ingredientCommand.description)
						.param("amount", ingredientCommand.amount.toString())
						.param("unitOfMeasure.id", uom.id.toString()))
				.andExpect(status().is3xxRedirection)
				.andExpect(view().name("redirect:/recipe/$recipeId/ingredient/${ingredientCommand.id}/show"))
		
	}
	
}