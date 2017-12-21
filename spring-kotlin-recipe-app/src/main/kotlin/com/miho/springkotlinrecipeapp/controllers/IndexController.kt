package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController(private val recipeService: RecipeService) {
	
	@GetMapping("", "/", "/index")
	fun getIndexPage(model: Model): String {
		
		model.addAttribute("recipes", recipeService.getAllRecipes())
		
		return "index"
	}
}