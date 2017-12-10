package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.ui.Model
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping

@Controller
@RequestMapping("/recipe")
class RecipeController(private val recipeService: RecipeService) {


	@GetMapping
	@RequestMapping("/{id}/show")
	fun showById(@PathVariable id: String, model: Model): String {

		model.addAttribute("recipe", recipeService.findById(id.toLong()))

		return "recipe/show"
	}

	@GetMapping
	@RequestMapping("/new")
	fun newRecipe(model: Model): String {

		model.addAttribute("recipe", RecipeCommand())

		return "recipe/recipeform"

	}

	@PostMapping
	@RequestMapping("")
	fun saveOrUpdate(@ModelAttribute command: RecipeCommand): String {

		val savedCommand = recipeService.saveRecipe(command)

		return "redirect:/recipe/${savedCommand?.id}/show"
	}

	@GetMapping
	@RequestMapping("/{id}/update")
	fun updateRecipe(@PathVariable id: String, model: Model): String {

		model.addAttribute("recipe", recipeService.findById(id.toLong()))
		
		return "recipe/recipeform"
	}
	
	@GetMapping
	@RequestMapping("/{id}/delete")
	fun deleteRecipe(@PathVariable id: String): String {

		recipeService.deleteById(id.toLong())
		
		return "redirect:/"
	}

}