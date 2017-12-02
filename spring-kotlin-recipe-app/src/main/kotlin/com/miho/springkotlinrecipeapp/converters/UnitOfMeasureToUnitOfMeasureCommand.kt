package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import org.springframework.stereotype.Component
import kotlin.jvm.Synchronized

@Component
class UnitOfMeasureToUnitOfMeasureCommand : Converter<UnitOfMeasure, UnitOfMeasureCommand> {
	
	@Synchronized
	override fun convert(source: UnitOfMeasure?): UnitOfMeasureCommand? {
	
		if (source == null)
			return null
				
		return UnitOfMeasureCommand(source.id, source.unit)
	}
}