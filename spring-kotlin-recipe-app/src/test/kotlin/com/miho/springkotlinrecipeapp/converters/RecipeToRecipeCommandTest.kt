package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.domain.*
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class RecipeToRecipeCommandTest {


	private val RECIPE_ID = 1L
	private val COOK_TIME = 5
	private val PREP_TIME = 7
	private val DESCRIPTION = "My Recipe"
	private val DIRECTIONS = "Directions"
	private val DIFFICULTY = Difficulty.EASY
	private val SERVINGS = 3
	private val SOURCE = "Source"
	private val URL = "Some URL"
	private val CAT_ID_1 = 1L
	private val CAT_ID2 = 2L
	private val INGRED_ID_1 = 3L
	private val INGRED_ID_2 = 4L
	private val NOTES_ID = 9L
	private val IMAGE = "some fake image".byteInputStream().use { it.readBytes() }

	private var converter: RecipeToRecipeCommand? = null

	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = RecipeToRecipeCommand(NotesToNotesCommand(),
				IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand()), CategoryToCategoryCommand()
		)
	}

	@Test
	@Throws(Exception::class)
	fun testNullObject() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(converter!!.convert(Recipe()))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		
		val notes = Notes(id = NOTES_ID)
		
		val category = Category(id = CAT_ID_1)
		val category2 = Category(id = CAT_ID2)
		val categories = mutableSetOf(category, category2)
		
		
		val ingredient = Ingredient(id = INGRED_ID_1)
		val ingredient2 = Ingredient(id = INGRED_ID_2)
		val ingredients = mutableSetOf(ingredient, ingredient2)
		
		val recipe = Recipe(id = RECIPE_ID, cookTime = COOK_TIME, prepTime = PREP_TIME, description = DESCRIPTION, difficulty = DIFFICULTY,
				directions = DIRECTIONS, servings = SERVINGS, source = SOURCE, url = URL, notes = notes, categories = categories, ingredients = ingredients, image = IMAGE)
		
//when
		val command = converter!!.convert(recipe)
//then
		assertNotNull(command)
		assertEquals(RECIPE_ID, command!!.id)
		assertEquals(COOK_TIME, command.cookTime)
		assertEquals(PREP_TIME, command.prepTime)
		assertEquals(DESCRIPTION, command.description)
		assertEquals(DIFFICULTY, command.difficulty)
		assertEquals(DIRECTIONS, command.directions)
		assertEquals(SERVINGS, command.servings)
		assertEquals(SOURCE, command.source)
		assertEquals(URL, command.url)
		assertEquals(NOTES_ID, command.notes?.id)
		assertEquals(2, command.categories.size)
		assertEquals(2, command.ingredients.size)
		assertEquals(IMAGE.size, command.image.size)
	}


}