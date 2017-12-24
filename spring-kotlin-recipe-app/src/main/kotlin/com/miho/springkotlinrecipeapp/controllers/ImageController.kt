package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import com.miho.springkotlinrecipeapp.services.RecipeService
import com.miho.springkotlinrecipeapp.services.ImageService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("/recipe")
class ImageController(private val recipeService: RecipeService, private val imageService: ImageService) {
	
	@GetMapping("/{recipeId}/image")
	fun getImageForm(@PathVariable recipeId: String, model: Model): String{
		
		model.addAttribute("recipe", recipeService.findById(recipeId.toLong()))
		
		return "imageuploadform"
	}
	
	@PostMapping("/{recipeId}/image/upload")
	fun handleImagePost(@PathVariable recipeId: String, @RequestParam("imagefile") file: MultipartFile): String{
		
		imageService.saveImageFile(recipeId.toLong(), file)
		
		return "redirect:/recipe/$recipeId/show"
	}
}