package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.repositories.UnitOfMeasureRepository
import org.mockito.Mock
import org.junit.Before
import org.mockito.MockitoAnnotations
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand
import org.junit.Test
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import org.mockito.Mockito.`when` as mockitoWhen
import org.mockito.Matchers.anyLong
import org.junit.Assert.assertEquals
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class UnitOfMeasureServiceImplTest {
	
	private lateinit var uomService: UnitOfMeasureService
	
	@Mock
	private lateinit var uomRepository: UnitOfMeasureRepository
	
	@Before
	fun setUp(){
		
		MockitoAnnotations.initMocks(this)
		
		val uomToCommand = UnitOfMeasureToUnitOfMeasureCommand()
		
		uomService = UnitOfMeasureServiceImpl(uomRepository, uomToCommand)
	}
	
	@Test
	fun testListAllUoms(){
//		given
		val uom1 = UnitOfMeasure(id = 1)
		val uom2 = UnitOfMeasure(id = 2)
		val uom3 = UnitOfMeasure(id = 3)
		
		val uoms = setOf(uom1, uom2, uom3)
		
//		when
		mockitoWhen(uomRepository.findAll()).thenReturn(uoms)
		
		val uomCommands = uomService.listAllUoms()

//		then
		assertEquals(uoms.size, uomCommands.size)
		verify(uomRepository, times(1)).findAll()
				
	}
}