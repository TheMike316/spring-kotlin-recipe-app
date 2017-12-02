package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by jt on 6/21/17.
 */
class UnitOfMeasureToUnitOfMeasureCommandTest {


	private val UNIT = "description"
	private val LONG_VALUE = 1L

	internal var converter: UnitOfMeasureToUnitOfMeasureCommand? = null

	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = UnitOfMeasureToUnitOfMeasureCommand()
	}

	@Test
	@Throws(Exception::class)
	fun testNullObjectConvert() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObj() {
		assertNotNull(converter!!.convert(UnitOfMeasure()))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val uom = UnitOfMeasure(UNIT, LONG_VALUE)
//when
		val uomc = converter!!.convert(uom)
//then
		assertEquals(LONG_VALUE, uomc!!.id)
		assertEquals(UNIT, uomc.unit)
	}

}