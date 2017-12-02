package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.domain.Category
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CategoryCommandToCategory : Converter<CategoryCommand, Category> {
	
	@Synchronized
	override fun convert(source: CategoryCommand?): Category?{
		
		if (source == null)
			return null;
		
		return Category(description = source.description, id = source.id)
		
		
	}
}