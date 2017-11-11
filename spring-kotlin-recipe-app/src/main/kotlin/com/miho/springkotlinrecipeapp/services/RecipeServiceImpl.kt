package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.domain.Recipe
import javax.transaction.Transactional

@Service
@Transactional
open class RecipeServiceImpl(private val recipeRepository: RecipeRepository) : RecipeService {
	
	
	override fun getAllRecipes() = recipeRepository.findAll().toSet()
	
	override fun saveAll(recipes: Iterable<Recipe>) = recipeRepository.saveAll(recipes)
}