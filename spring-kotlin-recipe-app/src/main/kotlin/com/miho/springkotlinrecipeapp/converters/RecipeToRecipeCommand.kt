package com.miho.springkotlinrecipeapp.converters

import org.springframework.core.convert.converter.Converter
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.springframework.stereotype.Component
import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.commands.IngredientCommand
import com.miho.springkotlinrecipeapp.commands.CategoryCommand

@Component
class RecipeToRecipeCommand (val notesConverter: NotesToNotesCommand,
							 val ingredientConverter: IngredientToIngredientCommand, val categoryConverter: CategoryToCategoryCommand) : Converter<Recipe, RecipeCommand>{
	
	@Synchronized
	override fun convert(source: Recipe?): RecipeCommand? {
		
		if (source == null)
			return null
		
		val convertedNotes = notesConverter.convert(source.notes)
		
		val notes = if (convertedNotes != null) convertedNotes else NotesCommand(-1, "")		
		
		return RecipeCommand(source.description, source.prepTime, source.cookTime, source.servings, source.source, source.url, source.directions,
				 			notes, source.ingredients.asSequence().map { ingredientConverter.convert(it) }
															      .filter { it != null }
				                                                  .map { it as IngredientCommand }
				                                                  .toMutableSet(),
				            source.difficulty, source.categories.asSequence().map { categoryConverter.convert(it) }
				                                                             .filter { it != null }
				                                                             .map { it as CategoryCommand }
				                                                             .toMutableSet(), source.id )
		
		
	}
}