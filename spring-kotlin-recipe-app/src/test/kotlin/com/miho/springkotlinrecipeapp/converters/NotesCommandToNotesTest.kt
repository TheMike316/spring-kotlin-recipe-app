package com.miho.springkotlinrecipeapp.converters

import com.miho.springkotlinrecipeapp.commands.NotesCommand
import com.miho.springkotlinrecipeapp.domain.Notes
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class NotesCommandToNotesTest {


	private val ID_VALUE = 1L
	private val RECIPE_NOTES = "Notes"

	
	private var converter: NotesCommandToNotes? = null
	@Before
	@Throws(Exception::class)
	fun setUp() {
		converter = NotesCommandToNotes()
	}

	@Test
	@Throws(Exception::class)
	fun testNullParameter() {
		assertNull(converter!!.convert(null))
	}

	@Test
	@Throws(Exception::class)
	fun testEmptyObject() {
		assertNotNull(converter!!.convert(NotesCommand(-1, "")))
	}

	@Test
	@Throws(Exception::class)
	fun convert() {
//given
		val notesCommand = NotesCommand(ID_VALUE, RECIPE_NOTES)
//when
		val notes = converter!!.convert(notesCommand)
//then
		assertNotNull(notes)
		assertEquals(ID_VALUE, notes!!.id)
		assertEquals(RECIPE_NOTES, notes.recipeNotes)
	}


}