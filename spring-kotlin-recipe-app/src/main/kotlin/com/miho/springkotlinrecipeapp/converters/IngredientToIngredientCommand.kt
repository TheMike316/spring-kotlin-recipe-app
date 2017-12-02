package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.domain.Ingredient
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand
import org.springframework.stereotype.Component

@Component
class IngredientToIngredientCommand (val unitConverter: UnitOfMeasureToUnitOfMeasureCommand) : Converter<Ingredient, IngredientCommand>{
	
	@Synchronized
	override fun convert(source: Ingredient?): IngredientCommand? {
		
		if (source == null)
			return null;
		
		val convertedUnit = unitConverter.convert(source.unitOfMeasure)
				
		return IngredientCommand(source.id, source.description, source.amount, convertedUnit)
		
		
	}
}