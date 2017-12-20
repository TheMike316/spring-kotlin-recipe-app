package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import org.springframework.transaction.annotation.Transactional
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository

@Service
open class IngredientServiceImpl(private val ingredientRepository: IngredientRepository, private val recipeRepository: RecipeRepository,
								 private val ingredientToCommand: IngredientToIngredientCommand, private val commandToIngredient: IngredientCommandToIngredient) : IngredientService {

	override fun findByRecipeIdAndIngredientId(recipeId: Long, id: Long): IngredientCommand? {

		val ingredient = ingredientRepository.findByRecipeIdAndId(recipeId, id)

		if (ingredient == null)
			throw RuntimeException("Ingredient not found!")

		return ingredientToCommand.convert(ingredient)
	}

	@Transactional
	override fun saveOrUpdateIngredient(ingredientCommand: IngredientCommand?): IngredientCommand {

		if (ingredientCommand?.id == -1L)
			return saveNewIngredient(ingredientCommand)

		return updateIngredient(ingredientCommand)
	}

	private fun saveNewIngredient(ingredientCommand: IngredientCommand?): IngredientCommand {

		val ingredient = commandToIngredient.convert(ingredientCommand)

		if (ingredient == null)
			throw RuntimeException("Internal Error")

		val recipeOptional = recipeRepository.findById(ingredientCommand?.recipeId)

		if (!recipeOptional.isPresent)
			throw RuntimeException("Internal Error")

		recipeOptional.get().addIngredient(ingredient)

		val savedRecipe = recipeRepository.save(recipeOptional.get())

//		not totally safe since none of these properties have to be unique....but best guess 
		val savedIngredient = savedRecipe.ingredients.asSequence().filter { it.description == ingredientCommand?.description }
				.filter { it.amount == ingredientCommand?.amount }
				.filter { it.unitOfMeasure?.id == ingredientCommand?.unitOfMeasure?.id }
				.first()

		val savedIngredientCommand = ingredientToCommand.convert(savedIngredient)

		if (savedIngredientCommand == null)
			throw RuntimeException("Internal Error")

		return savedIngredientCommand
	}

	private fun updateIngredient(ingredientCommand: IngredientCommand?): IngredientCommand {

		val ingredient = ingredientRepository.save(commandToIngredient.convert(ingredientCommand))

		val savedCommand = ingredientToCommand.convert(ingredient)

		if (savedCommand == null)
			throw RuntimeException("Internal Error")

		return savedCommand
	}

	@Transactional
	override fun deleteById(recipeId: Long, ingredientId: Long) {

		val recipeOpt = recipeRepository.findById(recipeId)

		if (recipeOpt.isPresent && recipeOpt.get().ingredients.firstOrNull { it.id == ingredientId } != null){
			
			recipeOpt.get().ingredients.removeIf{ it.id == ingredientId }
			
			recipeRepository.save(recipeOpt.get())
			
			ingredientRepository.deleteById(ingredientId)
		
			
		}else
			throw RuntimeException("Internal Error")

	}
}