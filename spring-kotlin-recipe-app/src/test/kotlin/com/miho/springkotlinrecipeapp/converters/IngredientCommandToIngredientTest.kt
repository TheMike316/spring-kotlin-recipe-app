package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.Ingredient
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import org.junit.Assert.*

class IngredientCommandToIngredientTest {
	
	
	private	val RECIPE = Recipe()
	private	val AMOUNT = BigDecimal("1")
	private	val DESCRIPTION = "Cheeseburger"
	private	val ID_VALUE = 1L
	private	val UOM_ID = 2L
	
	private var converter: IngredientCommandToIngredient? = null
	
	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure())
	}

	@Test
	@Throws(Exception::class)
	fun testNullObject() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(converter!!.convert(IngredientCommand(1, "", BigDecimal.ZERO, UnitOfMeasureCommand(1, ""))))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		
		val unitOfMeasureCommand = UnitOfMeasureCommand(UOM_ID, "")
		val command = IngredientCommand(ID_VALUE, DESCRIPTION, AMOUNT, unitOfMeasureCommand)
//when
		val ingredient = converter!!.convert(command)
//then
		assertNotNull(ingredient)
		assertNotNull(ingredient!!.unitOfMeasure)
		assertEquals(ID_VALUE, ingredient.id)
		assertEquals(AMOUNT, ingredient.amount)
		assertEquals(DESCRIPTION, ingredient.description)
		assertEquals(UOM_ID, ingredient.unitOfMeasure!!.id)
	}

	@Test
	@Throws(Exception::class)
	fun convertWithNullUOM() {
//given
		
		val command = IngredientCommand(ID_VALUE, DESCRIPTION, AMOUNT)
//when
		val ingredient = converter!!.convert(command)
//then
		assertNotNull(ingredient)
		assertNull(ingredient!!.unitOfMeasure)
		assertEquals(ID_VALUE, ingredient.id)
		assertEquals(AMOUNT, ingredient.amount)
		assertEquals(DESCRIPTION, ingredient.description)
	}

	
}