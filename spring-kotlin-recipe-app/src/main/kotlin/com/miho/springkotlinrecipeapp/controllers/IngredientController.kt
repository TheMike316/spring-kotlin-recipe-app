package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.services.IngredientService
import com.miho.springkotlinrecipeapp.services.RecipeService
import com.miho.springkotlinrecipeapp.services.UnitOfMeasureService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/recipe")
class IngredientController(private val recipeService: RecipeService,
						   private val ingredientService: IngredientService, private val uomService: UnitOfMeasureService) {

	@GetMapping("/{id}/ingredients")
	fun getIngredientList(@PathVariable id: String, model: Model): String {

		model.addAttribute("recipe", recipeService.findById(id.toLong()))

		return "recipe/ingredient/list"
	}

	@GetMapping("/{recipeId}/ingredient/{ingredientId}/show")
	fun showById(@PathVariable recipeId: String, @PathVariable ingredientId: String, model: Model): String {

		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId.toLong(), ingredientId.toLong()))

		return "recipe/ingredient/show"
	}

	@GetMapping("/{recipeId}/ingredient/{ingredientId}/update")
	fun updateIngredient(@PathVariable recipeId: String, @PathVariable ingredientId: String, model: Model): String {

		model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId.toLong(), ingredientId.toLong()))

		model.addAttribute("uomList", uomService.listAllUoms())

		return "recipe/ingredient/ingredientform"

	}

	@GetMapping("/{recipeId}/ingredient/new")
	fun newIngredient(@PathVariable recipeId: String, model: Model): String {

		val recipeCommand = recipeService.findById(recipeId.toLong())
//		TODO raise Exception if null

		if (recipeCommand != null)
			model.addAttribute("ingredient", IngredientCommand(recipeId = recipeCommand.id, unitOfMeasure = UnitOfMeasureCommand()))

		model.addAttribute("uomList", uomService.listAllUoms())

		return "recipe/ingredient/ingredientform"

	}

	@PostMapping("/{recipeId}/ingredient")
	fun saveOrUpdate(@ModelAttribute ingredient: IngredientCommand, @PathVariable recipeId: String): String {

		val savedIngredient = ingredientService.saveOrUpdateIngredient(ingredient)

		return "redirect:/recipe/$recipeId/ingredient/${savedIngredient.id}/show"

	}
	
	@GetMapping("/{recipeId}/ingredient/{ingredientId}/delete")
	fun delete(@PathVariable recipeId: String, @PathVariable ingredientId: String): String {
		
		ingredientService.deleteById(recipeId.toLong() ,ingredientId.toLong())
		
		return "redirect:/recipe/$recipeId/ingredients"
	}
}