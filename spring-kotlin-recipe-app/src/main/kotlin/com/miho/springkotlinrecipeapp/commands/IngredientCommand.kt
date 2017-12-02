package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import java.math.BigDecimal

data class IngredientCommand(var id: Long, var description: String , var amount: BigDecimal, var unitOfMeasure: UnitOfMeasureCommand? = null)