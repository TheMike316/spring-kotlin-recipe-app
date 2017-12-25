package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.Ingredient
import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.repositories.IngredientRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as mockitoWhen
import org.mockito.Matchers.anyLong
import org.mockito.Matchers.any
import org.junit.Assert.assertEquals
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import java.util.Optional
import org.springframework.mock.web.MockMultipartFile
import org.mockito.ArgumentCaptor

class ImageServiceImplTest {
	
	private lateinit var imageService: ImageService
	
	@Mock
	private lateinit var recipeRepository: RecipeRepository
	
	@Before
	fun startUp(){
		
		MockitoAnnotations.initMocks(this)
		
		imageService = ImageServiceImpl(recipeRepository)
	}
	
	
	@Test
	fun testSaveImageFile(){
		
//		given
		val recipeId = 1L
		val file = MockMultipartFile("imagefile", "test.txt", "text/plain", "Mike kicks ass".byteInputStream())
		val recipe = Recipe(id = recipeId)
		
		mockitoWhen(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe))
		mockitoWhen(recipeRepository.save(any<Recipe>())).thenReturn(recipe)
		val argumentCaptor = ArgumentCaptor.forClass(Recipe::class.java)
		
//		when
		imageService.saveImageFile(recipeId, file)
		
//		then
		verify(recipeRepository, times(1)).findById(anyLong())
		verify(recipeRepository, times(1)).save(argumentCaptor.capture())
		val savedRecipe = argumentCaptor.value
		assertEquals(file.bytes.size, savedRecipe.image.size)
		
		
	}
	
}