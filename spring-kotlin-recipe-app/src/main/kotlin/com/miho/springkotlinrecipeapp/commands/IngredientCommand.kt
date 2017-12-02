package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import java.math.BigDecimal

data class IngredientCommand(val id: Long = -1, val description: String , val amount: BigDecimal, val unitOfMeasure: UnitOfMeasureCommand)