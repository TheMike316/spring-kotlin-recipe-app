package com.miho.springkotlinrecipeapp.controllers

import org.mockito.Mock
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.mockito.Mockito.`when` as mockitoWhen
import org.junit.Assert.*
import org.mockito.Mockito.*
import org.mockito.Matchers.anyLong
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

class RecipeControllerTest {
	
	private lateinit var controller: RecipeController
	
	
	@Mock
	private var service: RecipeService? = null
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		controller = RecipeController(service!!)
		
	}
	
	@Test
	fun testGetRecipe(){
		
		val recipe = Recipe(id = 1L)
		
		val mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
		
		mockitoWhen(service?.findById(anyLong()))?.thenReturn(recipe)
		
		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk).andExpect(view().name("recipe/show"))
		
		
	}
}