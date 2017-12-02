package com.miho.springkotlinrecipeapp.commands

data class CategoryCommand(val id: Long, val recipes: MutableSet<RecipeCommand>, val description: String) 