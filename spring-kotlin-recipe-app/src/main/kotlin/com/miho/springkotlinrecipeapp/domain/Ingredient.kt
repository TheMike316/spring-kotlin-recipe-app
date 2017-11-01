package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
data class Ingredient(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null, var description: String, var amount: BigDecimal,
					  @ManyToOne var recipe: Recipe, @OneToOne var unitOfMeasure: UnitOfMeasure)
