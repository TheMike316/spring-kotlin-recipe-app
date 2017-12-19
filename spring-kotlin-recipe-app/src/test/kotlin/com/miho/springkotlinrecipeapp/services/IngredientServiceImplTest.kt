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
import org.junit.Assert.assertEquals
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import java.util.Optional

class IngredientServiceImplTest {
	
	private lateinit var ingredientService: IngredientService
	
	@Mock
	private lateinit var ingredientRepository: IngredientRepository
	
	@Mock
	private lateinit var recipeRepository: RecipeRepository
	
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		val unitToCommand = UnitOfMeasureToUnitOfMeasureCommand()
		
		val ingredientToCommand = IngredientToIngredientCommand(unitToCommand)
		
		val commandToUnit = UnitOfMeasureCommandToUnitOfMeasure()
		
		val commandToIngredient = IngredientCommandToIngredient(commandToUnit)
		
		ingredientService = IngredientServiceImpl(ingredientRepository, recipeRepository, ingredientToCommand, commandToIngredient)		
		
		
	}
	 
	@Test
	fun testFindByRecipeIdAndIngredientId(){
//		given
		val recipe = Recipe(id = 1)
		
		val ingredient1 = Ingredient(id = 1)
		val ingredient2 = Ingredient(id = 2)
		val ingredient3 = Ingredient(id = 3)
		
		recipe.addIngredients(listOf(ingredient1, ingredient2, ingredient3))
		

		mockitoWhen(ingredientRepository.findByRecipeIdAndId(anyLong(), anyLong())).thenReturn(ingredient2)
		
//      when		
		val ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1, 2)
		
//		then
		assertEquals(2L, ingredientCommand?.id)
		assertEquals(1L, ingredientCommand?.recipeId)
		verify(ingredientRepository, times(1)).findByRecipeIdAndId(anyLong(), anyLong())
	}
	
	@Test
	fun testDeleteByIdHappyPath(){
		
//		given
		val ingredient = Ingredient(id = 2)
		val recipe = Recipe(id = 1, ingredients = mutableSetOf(ingredient))
	
//	 	when
		mockitoWhen(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe))
		ingredientService.deleteById(recipe.id, ingredient.id)
		
//		then
		verify(ingredientRepository, times(1)).deleteById(anyLong())
	}
	
	@Test(expected=RuntimeException::class)
	fun testDeleteByIdSadPath(){
//		given
		val recipe = Recipe (id = 2)
		val ingredientId = 2L
	
//	 	when
		mockitoWhen(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe))
		ingredientService.deleteById(recipe.id, ingredientId)
		
//		then expected exception is thrown
	}
	
}