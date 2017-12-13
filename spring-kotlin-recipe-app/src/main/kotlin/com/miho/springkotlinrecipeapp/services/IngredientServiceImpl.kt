package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand

@Service
class IngredientServiceImpl(private val ingredientRepository: IngredientRepository, private val ingredientToCommand: IngredientToIngredientCommand) : IngredientService {

	override fun findByRecipeIdAndIngredientId(recipeId: Long, id: Long): IngredientCommand? {
		
		val ingredient = ingredientRepository.findByRecipeIdAndId(recipeId, id)
		
		if (ingredient == null)
			throw RuntimeException("Ingredient not found!")
		
		return ingredientToCommand.convert(ingredient)
	}
	
}