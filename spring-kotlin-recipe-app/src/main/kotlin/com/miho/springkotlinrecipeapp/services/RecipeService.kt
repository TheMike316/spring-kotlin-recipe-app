package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.commands.RecipeCommand

interface RecipeService {
	
	fun getAllRecipes(): Set<RecipeCommand>
	
	fun findById(id: Long): RecipeCommand?
	
	fun saveAll(recipes: Iterable<RecipeCommand>): Iterable<RecipeCommand>
	
	fun saveRecipe(recipeCommand: RecipeCommand): RecipeCommand?
}