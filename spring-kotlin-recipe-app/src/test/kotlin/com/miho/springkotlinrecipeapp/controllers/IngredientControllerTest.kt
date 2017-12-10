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

class IngredientControllerTest {
	
	private lateinit var controller: IngredientController
	
	private lateinit var mockMvc: MockMvc	
	
	@Mock
	private lateinit var service: RecipeService
	
	@Before
	fun startUp(){
		MockitoAnnotations.initMocks(this)
		
		controller = IngredientController(service)
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
	}
	
	@Test
	fun testListIngredients(){
//		given
		val recipeCommand = RecipeCommand()
		mockitoWhen(service.findById(anyLong())).thenReturn(recipeCommand)
		
//		when
		mockMvc.perform(get("/recipe/1/ingredients"))
				.andExpect(status().isOk)
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(model().attributeExists("recipe"))
		
	}
}