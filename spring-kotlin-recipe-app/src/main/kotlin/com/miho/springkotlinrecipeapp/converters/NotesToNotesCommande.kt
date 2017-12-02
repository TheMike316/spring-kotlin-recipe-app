package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.domain.Notes
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import org.springframework.stereotype.Component
import kotlin.jvm.Synchronized

@Component
class NotesToNotesCommand : Converter<Notes, NotesCommand>{
	
	@Synchronized
	override fun convert(source: Notes?): NotesCommand? {
		
		if (source == null)
			return null
		
		return NotesCommand(source.id, source.recipeNotes)
		
	}
}