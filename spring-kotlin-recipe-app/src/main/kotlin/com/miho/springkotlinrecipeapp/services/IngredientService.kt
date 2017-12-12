package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.IngredientCommand

interface IngredientService {

	fun findByRecipeIdAndId(recipeId: Long, id: Long): IngredientCommand?
}