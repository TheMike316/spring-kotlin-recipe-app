package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.domain.Recipe
import com.miho.springkotlinrecipeapp.repositories.RecipeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import com.miho.springkotlinrecipeapp.commands.RecipeCommand
import com.miho.springkotlinrecipeapp.converters.RecipeToRecipeCommand
import com.miho.springkotlinrecipeapp.converters.RecipeCommandToRecipe

@Service
@Transactional
open class RecipeServiceImpl(private val recipeRepository: RecipeRepository, private val recipeToCommand: RecipeToRecipeCommand,
							 private val commandToRecipe: RecipeCommandToRecipe) : RecipeService {


	override fun findById(id: Long): RecipeCommand? {

		val recipe = recipeRepository.findById(id)

		if (recipe.isPresent)
			return recipeToCommand.convert(recipe.get())

		throw RuntimeException("Recipe not found!")


	}

	override fun getAllRecipes() = recipeRepository.findAll().asSequence().map(recipeToCommand::convert).filter { it != null }.map { it as RecipeCommand }.toSet()

	override fun saveAll(recipes: Iterable<RecipeCommand>): Iterable<RecipeCommand> {

		val savedRecipes = recipeRepository.saveAll(recipes.asSequence()
				.map(commandToRecipe::convert)
				.filter { it != null }
				.map { it as Recipe }
				.toList())

		return savedRecipes.asSequence().map(recipeToCommand::convert).filter { it != null }.map { it as RecipeCommand }.toList()
	}

	override fun saveRecipe(recipeCommand: RecipeCommand?): RecipeCommand {
		
		if (recipeCommand == null)
			throw RuntimeException("internal Error")

		val savedRecipe = recipeRepository.save(commandToRecipe.convert(recipeCommand))

		val savedRecipeCommand = recipeToCommand.convert(savedRecipe)
		
		if (savedRecipeCommand == null)
			throw RuntimeException("internal Error")
		
		return savedRecipeCommand

	}
	
	override fun deleteById(id: Long) = recipeRepository.deleteById(id)
}