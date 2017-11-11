package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class RecipeServiceImpl(private val recipeRepository: RecipeRepository) : RecipeService {
	
	
	override fun getAllRecipes() = recipeRepository.findAll().toSet()
	
	override fun saveAll(recipes: Iterable<Recipe>) = recipeRepository.saveAll(recipes)
}