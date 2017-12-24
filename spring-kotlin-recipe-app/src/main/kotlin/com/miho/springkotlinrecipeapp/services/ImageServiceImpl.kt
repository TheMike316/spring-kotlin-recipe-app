package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.transaction.annotation.Transactional

@Service
class ImageServiceImpl(private val recipeRepository: RecipeRepository): ImageService {
	
	@Transactional
	override fun saveImageFile(recipeId: Long, image: MultipartFile?) {
		
		if (image == null)
			throw RuntimeException("Internal Error")

		val recipeOpt = recipeRepository.findById(recipeId)
		
		if (!recipeOpt.isPresent)
			throw RuntimeException("Recipe not found")
		
		recipeOpt.get().image = image.bytes
		
		val savedRecipe = recipeRepository.save(recipeOpt.get())
		
		if (savedRecipe == null)
			throw RuntimeException("Internal Error")
	}
}