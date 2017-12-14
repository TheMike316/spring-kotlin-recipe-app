package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand

interface UnitOfMeasureService {
	
	fun listAllUoms(): Set<UnitOfMeasureCommand>
}