package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.Difficulty

data class RecipeCommand(var description: String, var prepTime: Int, var cookTime: Int, var servings: Int,
				  var source: String, var url: String, var directions: String,
				 var notes: NotesCommand, var ingredients: MutableSet<IngredientCommand>, var difficulty: Difficulty,
			     var categories: MutableSet<CategoryCommand>, var id: Long = -1)