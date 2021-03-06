package com.miho.springkotlinrecipeapp.repositories

import org.springframework.data.repository.CrudRepository
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure

interface UnitOfMeasureRepository: CrudRepository<UnitOfMeasure, Long>{
	
	fun findByUnit(unit: String): UnitOfMeasure?
}