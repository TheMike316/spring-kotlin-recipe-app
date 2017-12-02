package com.miho.springkotlinrecipeapp.commands

data class NotesCommand(val id: Long, val recipe: RecipeCommand, val recipeNotes: String)