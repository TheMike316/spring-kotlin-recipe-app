package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import org.springframework.stereotype.Component
import kotlin.jvm.Synchronized

@Component
class UnitOfMeasureCommandToUnitOfMeasure : Converter<UnitOfMeasureCommand, UnitOfMeasure>{
	
	@Synchronized
	override fun convert(source: UnitOfMeasureCommand?): UnitOfMeasure?{
		
		if (source == null)
			return null
		
		return UnitOfMeasure(source.unit, source.id)
		
	}
}