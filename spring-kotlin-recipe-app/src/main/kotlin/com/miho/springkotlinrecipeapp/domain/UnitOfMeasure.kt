package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class UnitOfMeasure (var unit: String = "",
						  
						 @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
					     var id: Long = -1)
