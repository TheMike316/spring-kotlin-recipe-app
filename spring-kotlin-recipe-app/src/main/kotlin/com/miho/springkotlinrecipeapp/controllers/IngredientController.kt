package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import com.miho.springkotlinrecipeapp.services.IngredientService

@Controller
class IngredientController (private val recipeService: RecipeService, private val ingredientService: IngredientService) {
	
	@RequestMapping("/recipe/{id}/ingredients")
	fun getIngredientList(@PathVariable id: String, model: Model): String{
		
		model.addAttribute("recipe", recipeService.findById(id.toLong()))
		
		return "recipe/ingredient/list"
	}
	
	@RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
	fun showById(@PathVariable recipeId: String, @PathVariable ingredientId: String, model: Model): String{
		
		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId.toLong(), ingredientId.toLong()))
		
		return "recipe/ingredient/show"
	}
}