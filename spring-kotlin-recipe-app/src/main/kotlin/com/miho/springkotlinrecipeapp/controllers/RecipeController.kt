package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.exceptions.NotFoundException
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid
import org.springframework.validation.BindingResult

@Controller
@RequestMapping("/recipe")
class RecipeController(private val recipeService: RecipeService) {


	@GetMapping("/{id}/show")
	fun showById(@PathVariable id: String, model: Model): String {

		model.addAttribute("recipe", recipeService.findById(id.toLong()))

		return "recipe/show"
	}

	@GetMapping("/new")
	fun newRecipe(model: Model): String {

		model.addAttribute("recipe", RecipeCommand())

		return "recipe/recipeform"

	}

	@PostMapping("")
	fun saveOrUpdate(@Valid @ModelAttribute("recipe") recipe: RecipeCommand, bindingResult: BindingResult): String {
		
		if (bindingResult.hasErrors())
			return "recipe/recipeform"

		val savedCommand = recipeService.saveRecipe(recipe)

		return "redirect:/recipe/${savedCommand?.id}/show"
	}

	@GetMapping("/{id}/update")
	fun updateRecipe(@PathVariable id: String, model: Model): String {

		model.addAttribute("recipe", recipeService.findById(id.toLong()))

		return "recipe/recipeform"
	}

	@GetMapping("/{id}/delete")
	fun deleteRecipe(@PathVariable id: String): String {

		recipeService.deleteById(id.toLong())

		return "redirect:/"
	}


}