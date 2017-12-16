package com.miho.springkotlinrecipeapp.services

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import org.springframework.beans.factory.annotation.Autowired
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import org.springframework.transaction.annotation.Transactional
import org.junit.Test
import org.junit.Assert.*
import com.miho.springkotlinrecipeapp.converters.RecipeToRecipeCommand

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

	@Transactional
	@Test
	fun testUpdateIngredient() {

//		given
		val ingredient = ingredientRepository.findAll().iterator().next()
		val ingredientCommand = ingredientToCommand.convert(ingredient)
		val recipeCommandBeforeUpdate = recipeToCommand.convert(ingredient.recipe)

//		when
		ingredientCommand?.description = NEW_DESCRIPTION
		val savedIngredientCommand = ingredientService.saveIngredient(ingredientCommand)
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


	}
}