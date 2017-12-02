package com.miho.springkotlinrecipeapp.converters

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import com.miho.springkotlinrecipeapp.converters.CategoryCommandToCategory
import com.miho.springkotlinrecipeapp.commands.CategoryCommand

class CategoryCommandToCategoryTest {
	
	private val ID_VALUE = 1L
	private	val DESCRIPTION = "description"
	
	private var conveter: CategoryCommandToCategory? = null
	
	@Before
	@Throws(Exception::class)
	fun setUp() {
		conveter = CategoryCommandToCategory()
	}

	@Test
	@Throws(Exception::class)
	fun testNullObject() {
		assertNull(conveter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(conveter!!.convert(CategoryCommand(-1, "")))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val categoryCommand = CategoryCommand(ID_VALUE, DESCRIPTION)
//when
		val category = conveter!!.convert(categoryCommand)
//then
		assertEquals(ID_VALUE, category!!.id)
		assertEquals(DESCRIPTION, category.description)
	}

	
}