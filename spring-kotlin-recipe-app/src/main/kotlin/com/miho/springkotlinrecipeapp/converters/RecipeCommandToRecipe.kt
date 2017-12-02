package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.domain.Recipe
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import com.miho.springkotlinrecipeapp.domain.Ingredient
import com.miho.springkotlinrecipeapp.commands.CategoryCommand
import com.miho.springkotlinrecipeapp.domain.Category

@Component
class RecipeCommandToRecipe (val noteConverter: NotesCommandToNotes,
							 val ingredientConverter: IngredientCommandToIngredient,
							 val categoryConverter: CategoryCommandToCategory) : Converter<RecipeCommand, Recipe> {
	
	
	@Synchronized
	override fun convert(source: RecipeCommand?): Recipe?{
		
		if (source == null)
			return null
		
		return Recipe(description = source.description, prepTime = source.prepTime, cookTime = source.cookTime,
				servings = source.servings, source = source.source, url = source.url, directions = source.directions,
				notes =noteConverter.convert(source.notes)
				, ingredients = source.ingredients.asSequence().map { ingredientConverter.convert(it) }
															   .filter { it != null }
															   .map { it as Ingredient }
															   .toMutableSet(),
				difficulty = source.difficulty,
				categories = source.categories.asSequence().map { categoryConverter.convert(it) }
		                                                   .filter { it != null }
		                                                   .map { it as Category }
		                                                   .toMutableSet(),
				id = source.id)
	}
}