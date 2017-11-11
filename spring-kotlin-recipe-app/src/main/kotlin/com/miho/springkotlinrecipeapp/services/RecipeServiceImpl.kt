package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.domain.Recipe

@Service
open class RecipeServiceImpl(private val recipeRepository: RecipeRepository) : RecipeService {
	
	override fun getAllRecipes() = recipeRepository.findAll().toSet()
}