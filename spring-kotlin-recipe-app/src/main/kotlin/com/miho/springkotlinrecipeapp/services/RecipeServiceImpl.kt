package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
@Transactional
open class RecipeServiceImpl(private val recipeRepository: RecipeRepository) : RecipeService {
	
	
	override fun findById(id: Long): Recipe {
		
	val recipe = recipeRepository.findById(id)
		
		if (recipe.isPresent)
			return recipe.get()
		
		throw RuntimeException("Recipe not found!")
	
		
	}
	
	override fun getAllRecipes() = recipeRepository.findAll().toSet()
	
	override fun saveAll(recipes: Iterable<Recipe>) = recipeRepository.saveAll(recipes)
}