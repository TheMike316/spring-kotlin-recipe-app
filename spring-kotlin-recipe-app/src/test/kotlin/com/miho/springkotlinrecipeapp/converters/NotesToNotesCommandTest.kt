package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.domain.Notes
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

/**
 * Created by jt on 6/21/17.
 */
class NotesToNotesCommandTest {


	private val ID_VALUE = 1L
	private val RECIPE_NOTES = "Notes"

	private var converter: NotesToNotesCommand? = null
	
	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = NotesToNotesCommand()
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val notes = Notes(id = ID_VALUE, recipeNotes = RECIPE_NOTES)
//when
		val notesCommand = converter!!.convert(notes)
//then
		assertEquals(ID_VALUE, notesCommand!!.id)
		assertEquals(RECIPE_NOTES, notesCommand.recipeNotes)
	}

	@Test
	@Throws(Exception::class)
	fun testNull() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(converter!!.convert(Notes()))
	}


}