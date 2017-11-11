package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.domain.Recipe

interface RecipeService {
	
	fun getAllRecipes(): Set<Recipe>
}