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

class IngredientServiceImplTest {
	
	private lateinit var ingredientService: IngredientService
	
	@Mock
	private lateinit var ingredientRepository: IngredientRepository
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		val unitToCommand = UnitOfMeasureToUnitOfMeasureCommand()
		
		val ingredientToCommand = IngredientToIngredientCommand(unitToCommand)
		
		ingredientService = IngredientServiceImpl(ingredientRepository, ingredientToCommand)		
		
		
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
}