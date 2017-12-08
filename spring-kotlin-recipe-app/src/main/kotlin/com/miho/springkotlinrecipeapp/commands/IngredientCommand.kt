package com.miho.springkotlinrecipeapp.commands

import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import java.math.BigDecimal

data class IngredientCommand(var id: Long = -1, var description: String = "", var amount: BigDecimal = BigDecimal.ZERO, var unitOfMeasure: UnitOfMeasureCommand? = null)