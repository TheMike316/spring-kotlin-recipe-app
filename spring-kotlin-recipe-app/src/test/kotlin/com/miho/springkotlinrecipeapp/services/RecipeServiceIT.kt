package com.miho.springkotlinrecipeapp.services

import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import com.miho.springkotlinrecipeapp.converters.RecipeCommandToRecipe
import com.miho.springkotlinrecipeapp.converters.RecipeToRecipeCommand
import org.springframework.transaction.annotation.Transactional
import org.junit.Test
import org.junit.Assert.*
import org.springframework.boot.test.context.SpringBootTest


//@DataJpaTest The test would not be able to find an implementation of the RecipeService when using this annotation,
//because then only a lightened version of our Spring Context would be brought up
@RunWith(SpringRunner::class)
@SpringBootTest
open class RecipeServiceIT {
	
	private val NEW_DESCRIPTION = "new description"
	
	@Autowired
	private lateinit var recipeService: RecipeService
	
	@Autowired
	private lateinit var recipeRepository: RecipeRepository
	
	@Autowired
	private lateinit var commandToRecipe: RecipeCommandToRecipe
	
	@Autowired
	private lateinit var recipeToCommand: RecipeToRecipeCommand
	
	
	@Transactional
	@Test
	fun testSaveOfDescription(){
//		given
		val testRecipe = recipeRepository.findAll().iterator().next()
		val testRecipeCommand = recipeToCommand.convert(testRecipe)
		
//		when
		testRecipeCommand?.description = NEW_DESCRIPTION
		val savedRecipeCommand = recipeService.saveRecipe(testRecipeCommand!!)
		
//		then
		assertNotNull(savedRecipeCommand)
		assertEquals(NEW_DESCRIPTION, savedRecipeCommand?.description)
		assertEquals(testRecipe.id, savedRecipeCommand?.id)
		assertEquals(testRecipe.categories.size, savedRecipeCommand?.categories?.size)
		assertEquals(testRecipe.ingredients.size, savedRecipeCommand?.ingredients?.size)
		
	}
}