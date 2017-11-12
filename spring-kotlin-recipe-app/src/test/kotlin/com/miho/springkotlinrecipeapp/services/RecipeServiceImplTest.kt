package com.miho.springkotlinrecipeapp.services

import org.junit.Test
import org.junit.Before
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.mockito.Mockito.`when` as mockitoWhen
import org.junit.Assert.assertEquals
import org.mockito.Mockito.verify
import org.mockito.Mockito.times

class RecipeServiceImplTest {
	
	private var service: RecipeServiceImpl? = null
	
	@Mock
	private var repository: RecipeRepository? = null
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)		
		
		service = RecipeServiceImpl(repository!!)
		
	}
	
	@Test
	fun getRecipes(){
		
		val mockRecipes = setOf(Recipe())
		
		mockitoWhen(service?.getAllRecipes()).thenReturn(mockRecipes)
		
		val recipes = service?.getAllRecipes()
		
		assertEquals(recipes?.size, 1)
		
		verify(repository, times(1))?.findAll()
		
	}
	
}