package com.miho.springkotlinrecipeapp.services

import com.miho.springkotlinrecipeapp.repositories.UnitOfMeasureRepository
import org.springframework.stereotype.Service
import com.miho.springkotlinrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand
import com.miho.springkotlinrecipeapp.commands.UnitOfMeasureCommand

@Service
class UnitOfMeasureServiceImpl(private val unitOfMeasureRepository: UnitOfMeasureRepository, private val uomToCommand: UnitOfMeasureToUnitOfMeasureCommand) : UnitOfMeasureService {
	
	override fun listAllUoms() = unitOfMeasureRepository.findAll().map(uomToCommand::convert).map { it as UnitOfMeasureCommand }.toSet()
}