package com.miho.springkotlinrecipeapp.repositories

import org.springframework.data.repository.CrudRepository
import com.miho.springkotlinrecipeapp.domain.Recipe

interface RecipeRepository: CrudRepository<Recipe, Long> 
