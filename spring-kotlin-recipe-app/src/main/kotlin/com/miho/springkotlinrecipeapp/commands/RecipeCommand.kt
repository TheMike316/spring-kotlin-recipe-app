package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.Difficulty

data class RecipeCommand(val description: String, val prepTime: Int, val cookTime: Int, val servings: Int,
				  val source: String, val url: String, val directions: String,
				 val notes: NotesCommand, val ingredients: MutableSet<IngredientCommand>, val difficulty: Difficulty,
			     val categories: MutableSet<CategoryCommand>, val id: Long = -1)