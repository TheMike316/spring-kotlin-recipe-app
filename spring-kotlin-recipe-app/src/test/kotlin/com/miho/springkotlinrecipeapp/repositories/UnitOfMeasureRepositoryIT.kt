package com.miho.springkotlinrecipeapp.repositories

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@RunWith(SpringRunner::class)
@DataJpaTest
open class UnitOfMeasureRepositoryIT() {
	
	@Autowired
	private lateinit var repository: UnitOfMeasureRepository
	
	@Test
	fun findByDescription(){
		
		assertNotEquals(null, repository)
		
		val unit = repository.findByUnit("Teaspoon")
		
		assertEquals("Teaspoon", unit?.unit)
	}
	
	@Test
	fun findByDescriptionCup(){
		
		assertNotEquals(null, repository)
		
		val unit = repository.findByUnit("Cup")
		
		assertEquals("Cup", unit?.unit)
	}
	
}