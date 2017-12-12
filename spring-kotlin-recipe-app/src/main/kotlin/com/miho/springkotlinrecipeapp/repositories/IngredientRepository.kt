package com.miho.springkotlinrecipeapp.repositories

import org.springframework.stereotype.Repository
import org.springframework.data.repository.CrudRepository
import com.miho.springkotlinrecipeapp.domain.Ingredient

@Repository
interface IngredientRepository : CrudRepository<Long, Ingredient> {
	
	fun findByRecipeIdAndId(recipeId: Long, id: Long): Ingredient?
}