package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.ui.Model

@Controller
@RequestMapping("/recipe")
class RecipeController (private val recipeService: RecipeService) {
	
	
	@RequestMapping("/show/{id}")
	fun showById(@PathVariable id: String, model: Model): String{
		
		model.addAttribute("recipe", recipeService.findById(id.toLong()))
		
		return "recipe/show"
	}
	
}