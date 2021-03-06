package com.miho.springkotlinrecipeapp.controllers

import com.miho.springkotlinrecipeapp.services.ImageService
import com.miho.springkotlinrecipeapp.services.RecipeService
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@Controller
@RequestMapping("/recipe")
class ImageController(private val recipeService: RecipeService, private val imageService: ImageService) {

    @GetMapping("/{recipeId}/image")
    fun getImageForm(@PathVariable recipeId: String, model: Model): String {

        model.addAttribute("recipe", recipeService.findById(recipeId.toLong()))

        return "recipe/imageuploadform"
    }

    @PostMapping("/{recipeId}/image/upload")
    fun handleImagePost(@PathVariable recipeId: String, @RequestParam("imagefile") file: MultipartFile): String {

        imageService.saveImageFile(recipeId.toLong(), file)

        return "redirect:/recipe/$recipeId/show"
    }

    @GetMapping("/{recipeId}/recipeimage")
    fun renderImageFromDb(@PathVariable recipeId: String, response: HttpServletResponse) {

        val recipeCommand = recipeService.findById(recipeId.toLong())

        response.contentType = "image/jpeg"

        val byteIS = recipeCommand?.image?.inputStream()
        IOUtils.copy(byteIS, response.outputStream)
    }
}