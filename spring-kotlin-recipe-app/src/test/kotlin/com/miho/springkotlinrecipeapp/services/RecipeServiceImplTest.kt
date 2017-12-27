package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.converters.CategoryCommandToCategory
import com.miho.springkotlinrecipeapp.converters.CategoryToCategoryCommand
import com.miho.springkotlinrecipeapp.converters.IngredientCommandToIngredient
import com.miho.springkotlinrecipeapp.converters.IngredientToIngredientCommand
import com.miho.springkotlinrecipeapp.converters.NotesCommandToNotes
import com.miho.springkotlinrecipeapp.converters.NotesToNotesCommand
import com.miho.springkotlinrecipeapp.converters.RecipeCommandToRecipe
import com.miho.springkotlinrecipeapp.converters.RecipeToRecipeCommand
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.Difficulty
import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyLong
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.Optional
import org.mockito.Mockito.`when` as mockitoWhen
import com.miho.springkotlinrecipeapp.exceptions.NotFoundException

class RecipeServiceImplTest {
	
	private var service: RecipeServiceImpl? = null
	
	
	
	@Mock
	private var repository: RecipeRepository? = null
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		val notesToCommand = NotesToNotesCommand()
		
		val unitToCommand = UnitOfMeasureToUnitOfMeasureCommand()
		
		val ingredientToCommand = IngredientToIngredientCommand(unitToCommand)
		
		val categoryToCommand = CategoryToCategoryCommand()
		
		val recipeToCommand = RecipeToRecipeCommand(notesToCommand, ingredientToCommand, categoryToCommand)
		
		val commandToNotes= NotesCommandToNotes()
		
		val commandToUnit = UnitOfMeasureCommandToUnitOfMeasure()
		
		val commandToIngredient = IngredientCommandToIngredient(commandToUnit)
		
		val commandToCategory = CategoryCommandToCategory()
		
		val commandToRecipe = RecipeCommandToRecipe(commandToNotes, commandToIngredient, commandToCategory)
		
		service = RecipeServiceImpl(repository!!, recipeToCommand, commandToRecipe)
		
	}
	
	@Test
	fun getRecipes(){
		
		val recipe = Recipe(id = 1L, description = "")
		
		val mockRecipes = setOf(recipe)
		
		mockitoWhen(repository?.findAll()).thenReturn(mockRecipes)
		
		val recipes = service?.getAllRecipes()
		
		assertEquals(recipes?.size, 1)
		
		verify(repository, times(1))?.findAll()
		
	}
	
	@Test
	fun getRecipeById(){
		val recipe = Recipe(id = 1L)
		
		mockitoWhen(repository?.findById(anyLong())).thenReturn(Optional.of(recipe))
		
		val recipeReturned = service?.findById(1L)
		
		assertNotNull("Null recipe returned", recipeReturned)
		
		verify(repository, times(1))?.findById(anyLong())
		verify(repository, never())?.findAll()
		
	}
	
	@Test
	fun deleteById(){
		
		val idToDelete = 2L
		
		service?.deleteById(idToDelete)
		
		verify(repository, times(1))?.deleteById(anyLong())
		
	}
	
	@Test(expected = NotFoundException::class)
	fun testFindByIdNotFound(){
//		given
		val recipeOptional: Optional<Recipe> = Optional.empty()
		
		mockitoWhen(repository?.findById(anyLong())).thenReturn(recipeOptional)
		
//		when
		service?.findById(4)
		
//		should go boom
	}
	
}