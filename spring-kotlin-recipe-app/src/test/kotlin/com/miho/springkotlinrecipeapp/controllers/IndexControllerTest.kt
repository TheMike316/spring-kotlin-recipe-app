package com.miho.springkotlinrecipeapp.controllers

import org.junit.Test
import org.junit.Before
import org.springframework.ui.Model
import org.mockito.Mock
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.Mockito.anySet
import org.mockito.Mockito.eq
import com.miho.springkotlinrecipeapp.domain.Recipe


class IndexControllerTest {
	
	private var controller: IndexController? = null
	
	@Mock
	private var model: Model? = null
	
	@Mock
	private var service: RecipeService? = null
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		controller = IndexController(service!!)
		
	}
	
	@Test
	fun getIndexPage(){
		
		assertEquals("index", controller?.getIndexPage(model!!))
		
		verify(model, times(1))?.addAttribute(eq("recipes"), anySet<Recipe>())
		
		verify(service, times(1))?.getAllRecipes()
		
		
		
	}
}