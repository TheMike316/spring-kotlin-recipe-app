package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient

@Service
class IngredientServiceImpl(private val ingredientRepository: IngredientRepository,
							private val ingredientToCommand: IngredientToIngredientCommand, private val commandToIngredient: IngredientCommandToIngredient) : IngredientService {

	override fun findByRecipeIdAndIngredientId(recipeId: Long, id: Long): IngredientCommand? {

		val ingredient = ingredientRepository.findByRecipeIdAndId(recipeId, id)

		if (ingredient == null)
			throw RuntimeException("Ingredient not found!")

		return ingredientToCommand.convert(ingredient)
	}

	override fun saveIngredient(ingredientCommand: IngredientCommand?): IngredientCommand {

		val ingredient = ingredientRepository.save(commandToIngredient.convert(ingredientCommand))

		val savedCommand = ingredientToCommand.convert(ingredient)
		
		if (savedCommand != null)		
			return savedCommand
		
		throw RuntimeException("Internal Error")
	}
}