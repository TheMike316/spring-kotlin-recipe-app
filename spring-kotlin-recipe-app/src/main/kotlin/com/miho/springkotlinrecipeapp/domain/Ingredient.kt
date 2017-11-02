package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
data class Ingredient(@field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
					  var id: Long = -1,
					  
					  var description: String = "",
					  					  
					  var amount: BigDecimal = BigDecimal.ZERO,
					  
					  @ManyToOne
					  var recipe: Recipe = Recipe(),
					  
					   @OneToOne
					  var unitOfMeasure: UnitOfMeasure = UnitOfMeasure())
