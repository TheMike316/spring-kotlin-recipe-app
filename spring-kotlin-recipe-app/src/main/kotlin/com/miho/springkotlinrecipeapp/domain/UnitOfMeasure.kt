package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class UnitOfMeasure (@field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
					     var id: Long = -1,
						 
						 var unit: String = "")
