package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand

interface IngredientService {

	fun findByRecipeIdAndIngredientId(recipeId: Long, ingredientId: Long): IngredientCommand?
	
	fun saveOrUpdateIngredient(ingredientCommand: IngredientCommand?): IngredientCommand
	
	fun deleteById(recipeId: Long, ingredientId: Long)
}