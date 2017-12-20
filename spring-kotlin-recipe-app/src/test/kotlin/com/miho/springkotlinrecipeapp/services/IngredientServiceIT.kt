package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.RecipeToRecipeCommand
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@RunWith(SpringRunner::class)
@SpringBootTest
open class IngredientServiceIT {

	private val NEW_DESCRIPTION = "new ingredient description"

	@Autowired
	private lateinit var ingredientRepository: IngredientRepository

	@Autowired
	private lateinit var ingredientService: IngredientService

	@Autowired
	private lateinit var recipeService: RecipeService

	@Autowired
	private lateinit var ingredientToCommand: IngredientToIngredientCommand

	@Autowired
	private lateinit var commandToIngredient: IngredientCommandToIngredient

	@Autowired
	private lateinit var recipeToCommand: RecipeToRecipeCommand
	
	@Autowired
	private lateinit var uomService: UnitOfMeasureService

	@Transactional
	@Test
	fun testUpdateIngredient() {

//		given
		val ingredient = ingredientRepository.findAll().iterator().next()
		val ingredientCommand = ingredientToCommand.convert(ingredient)
		val recipeCommandBeforeUpdate = recipeToCommand.convert(ingredient.recipe)

//		when
		ingredientCommand?.description = NEW_DESCRIPTION
		val savedIngredientCommand = ingredientService.saveOrUpdateIngredient(ingredientCommand)
		val recipeCommandAfterUpdate = recipeService.findById(savedIngredientCommand.recipeId)

		//		then
		assertNotNull(savedIngredientCommand)
		assertEquals(NEW_DESCRIPTION, savedIngredientCommand.description)
		assertEquals(ingredient.id, savedIngredientCommand.id)
		assertEquals(ingredient.recipe?.id, savedIngredientCommand.recipeId)
		assertEquals(ingredient.unitOfMeasure?.id, savedIngredientCommand.unitOfMeasure?.id)
		assertEquals(ingredient.amount, savedIngredientCommand.amount)
		
		assertEquals(recipeCommandBeforeUpdate?.id, recipeCommandAfterUpdate?.id)
		assertEquals(recipeCommandBeforeUpdate?.description, recipeCommandAfterUpdate?.description)
		assertEquals(recipeCommandBeforeUpdate?.categories, recipeCommandAfterUpdate?.categories)
		assertEquals(recipeCommandBeforeUpdate?.difficulty, recipeCommandAfterUpdate?.difficulty)
		assertEquals(recipeCommandBeforeUpdate?.ingredients?.size, recipeCommandAfterUpdate?.ingredients?.size)
		assert(recipeCommandAfterUpdate?.ingredients?.indexOf(savedIngredientCommand) != -1)


	}
	
	@Transactional
	@Test
	fun testNewIngredient(){
		
//		given
		val uom = uomService.listAllUoms().iterator().next()
		val recipe = recipeService.getAllRecipes().iterator().next()
		val newIngredientCommand = IngredientCommand(description = "new ingredient", recipeId = recipe.id, unitOfMeasure = uom, amount = BigDecimal.ONE)
		
//		when
		val savedIngredientCommand = ingredientService.saveOrUpdateIngredient(newIngredientCommand)
		val recipeCommand = recipeService.findById(recipe.id)
		
		
//		then
		assertNotNull(savedIngredientCommand)
		assertNotNull(savedIngredientCommand.id)
		assertEquals(savedIngredientCommand.recipeId, recipe.id)
		assertEquals(savedIngredientCommand.description, newIngredientCommand.description)
		assertEquals(savedIngredientCommand.unitOfMeasure, newIngredientCommand.unitOfMeasure)
		assertEquals(savedIngredientCommand.amount, newIngredientCommand.amount)
		assert(savedIngredientCommand.id != newIngredientCommand.id)
		assert(recipeCommand?.ingredients?.indexOf(savedIngredientCommand) != -1 )
	}
	
	@Transactional
	@Test
	fun testDeleteIngredientHappyPath(){
		
//		given
		val recipe = recipeService.getAllRecipes().iterator().next()
		val ingredient = recipe.ingredients.iterator().next()
		val recipeId = recipe.id
		val ingredientId = ingredient.id
		
//		when
		ingredientService.deleteById(recipeId, ingredientId)
		
//		then no exception is thrown
	}
	
	@Transactional
	@Test (expected = RuntimeException::class)
	fun testDeleteIngredientSadPath(){
		
//		given
		val recipe = recipeService.getAllRecipes().iterator().next()
		val ingredient = recipe.ingredients.iterator().next()
		val recipeId = recipe.id + 10000
		val ingredientId = ingredient.id
		
//		when
		ingredientService.deleteById(recipeId, ingredientId)
		
//		then an exception is thrown
	}
}