package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand

interface IngredientService {

	fun findByRecipeIdAndIngredientId(recipeId: Long, id: Long): IngredientCommand?
	
	fun saveOrUpdateIngredient(ingredientCommand: IngredientCommand?): IngredientCommand
}