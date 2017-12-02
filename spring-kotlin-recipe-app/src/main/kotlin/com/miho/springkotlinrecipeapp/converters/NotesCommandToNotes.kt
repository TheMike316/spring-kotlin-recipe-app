package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.domain.Notes
import org.springframework.stereotype.Component

@Component
class NotesCommandToNotes : Converter<NotesCommand, Notes> {
	
	@Synchronized
	override fun convert(source: NotesCommand?): Notes?{
		
		if (source == null)
			return null
		
		return Notes(recipeNotes = source.recipeNotes, id = source.id)
	}
}