package com.miho.springkotlinrecipeapp.services

import org.springframework.web.multipart.MultipartFile
import com.miho.springkotlinrecipeapp.commands.RecipeCommand

interface ImageService {
	
	fun saveImageFile(recipeId: Long, image: MultipartFile?)
}