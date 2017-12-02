package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.domain.Category
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import kotlin.jvm.Synchronized

@Component
class CategoryToCategoryCommand : Converter<Category, CategoryCommand> {
	
	@Synchronized
	override fun convert(source: Category?): CategoryCommand? {
		
		if (source == null)
			return null
		
		return CategoryCommand(source.id, source.description)
		
		
	}
}