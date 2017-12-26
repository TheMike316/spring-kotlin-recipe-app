package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.Difficulty

data class RecipeCommand(var description: String = "", var prepTime: Int = 0, var cookTime: Int = 0, var servings: Int = 0,
				  var source: String = "", var url: String = "", var directions: String = "",
				 var notes: NotesCommand? = null, var ingredients: MutableSet<IngredientCommand> = mutableSetOf(), var difficulty: Difficulty = Difficulty.EASY,
			     var categories: MutableSet<CategoryCommand> = mutableSetOf(), var image: ByteArray = byteArrayOf(), var id: Long = -1L)