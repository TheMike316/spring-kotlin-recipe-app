package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.domain.Ingredient
import org.springframework.stereotype.Component

@Component
class IngredientCommandToIngredient (val unitConverter: UnitOfMeasureCommandToUnitOfMeasure) : Converter<IngredientCommand, Ingredient> {
	
	@Synchronized
	override fun convert(source: IngredientCommand?): Ingredient? {
		
		if (source == null)
			return null
		
		return Ingredient(description = source.description, amount = source.amount, unitOfMeasure = unitConverter.convert(source.unitOfMeasure), id = source.id)
	}
}