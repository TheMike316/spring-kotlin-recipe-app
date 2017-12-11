package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.domain.Difficulty
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import java.math.BigDecimal

class RecipeCommandToRecipeTest {


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

	internal var converter: RecipeCommandToRecipe? = null

	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = RecipeCommandToRecipe(NotesCommandToNotes(),
				IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure()), CategoryCommandToCategory()
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
		assertNotNull(converter!!.convert(RecipeCommand("", 0, 0, 0, "", "", "", NotesCommand(1, ""), emptySet<IngredientCommand>().toMutableSet(),
				Difficulty.EASY, emptySet<CategoryCommand>().toMutableSet())))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val notes = NotesCommand(NOTES_ID, "")

		val category = CategoryCommand(CAT_ID_1, "")
		val category2 = CategoryCommand(CAT_ID2, "")

		val categories = mutableSetOf(category, category2)

		val uom = UnitOfMeasureCommand(1, "")

		val ingredient = IngredientCommand(id = INGRED_ID_1, description = "", amount = BigDecimal.ONE, unitOfMeasure = uom)
		val ingredient2 = IngredientCommand(id = INGRED_ID_2, description = "", amount = BigDecimal.ONE, unitOfMeasure = uom)

		val ingredients = mutableSetOf(ingredient, ingredient2)

		val recipeCommand = RecipeCommand(DESCRIPTION, PREP_TIME, COOK_TIME, SERVINGS, SOURCE, URL, DIRECTIONS, notes, ingredients, DIFFICULTY, categories, RECIPE_ID)


//when
		val recipe = converter!!.convert(recipeCommand)
		assertNotNull(recipe)
		assertEquals(RECIPE_ID, recipe!!.id)
		assertEquals(COOK_TIME, recipe.cookTime)
		assertEquals(PREP_TIME, recipe.prepTime)
		assertEquals(DESCRIPTION, recipe.description)
		assertEquals(DIFFICULTY, recipe.difficulty)
		assertEquals(DIRECTIONS, recipe.directions)
		assertEquals(SERVINGS, recipe.servings)
		assertEquals(SOURCE, recipe.source)
		assertEquals(URL, recipe.url)
		assertEquals(NOTES_ID, recipe.notes!!.id)
		assertEquals(2, recipe.categories.size)
		assertEquals(2, recipe.ingredients.size)
	}


}