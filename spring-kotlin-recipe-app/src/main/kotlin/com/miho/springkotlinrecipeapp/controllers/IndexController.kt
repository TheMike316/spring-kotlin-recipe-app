package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import com.miho.springkotlinrecipeapp.services.RecipeService

@Controller
class IndexController(private val recipeService: RecipeService) {
	
	@RequestMapping("", "/", "/index")
	fun getIndexPage(model: Model): String {
		
		model.addAttribute("recipes", recipeService.getAllRecipes())
		
		return "index"
	}
}