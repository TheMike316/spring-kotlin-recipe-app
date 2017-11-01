package com.miho.springkotlinrecipeapp.domain


data class Recipe(var description: String, var prepTime: Int, var cookTime: Int, var servings: Int, var source: String,
				   var url: String, var directions: String, var image: Array<Byte>, var notes: Notes)