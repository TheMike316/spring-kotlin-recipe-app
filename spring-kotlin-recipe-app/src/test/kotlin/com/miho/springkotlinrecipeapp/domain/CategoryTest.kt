package com.miho.springkotlinrecipeapp.domain

import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertEquals;

class CategoryTest {
	
	private var category: Category? = null
	
	@Before
	fun setUp(){
		category = Category()
	}
	
	@Test
	fun getId(){
		
		val idValue = 4L
		
		category?.id = idValue
		
		assertEquals(idValue, category?.id)
		
	}
	
	@Test
	fun getDescription(){
		
	}
	
	@Test
	fun getRecipes(){
		
	}
	
}