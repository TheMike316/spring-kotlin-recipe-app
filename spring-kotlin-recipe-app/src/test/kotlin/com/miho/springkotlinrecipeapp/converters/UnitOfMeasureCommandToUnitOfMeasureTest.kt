package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class UnitOfMeasureCommandToUnitOfMeasureTest {


	private val UNIT = "description"
	private val LONG_VALUE = 1L

	private var converter: UnitOfMeasureCommandToUnitOfMeasure? = null

	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = UnitOfMeasureCommandToUnitOfMeasure()
	}

	@Test
	@Throws(Exception::class)
	fun testNullParamter() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(converter!!.convert(UnitOfMeasureCommand(1, "")))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val command = UnitOfMeasureCommand(LONG_VALUE, UNIT)
//when
		val uom = converter!!.convert(command)
//then
		assertNotNull(uom)
		assertEquals(LONG_VALUE, uom!!.id)
		assertEquals(UNIT, uom.unit)
	}


}