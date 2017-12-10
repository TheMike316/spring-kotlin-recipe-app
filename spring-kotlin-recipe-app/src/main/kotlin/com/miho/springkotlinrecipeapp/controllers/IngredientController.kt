package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class IngredientController (private val recipeService: RecipeService) {
	
	@RequestMapping("recipe/{id}/ingredients")
	fun getIngredientList(@PathVariable id: String, model: Model): String{
		
		model.addAttribute("recipe", recipeService.findById(id.toLong()))
		
		return "recipe/ingredient/list"
	}
}