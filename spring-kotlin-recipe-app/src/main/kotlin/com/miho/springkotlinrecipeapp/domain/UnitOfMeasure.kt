package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity
data class UnitOfMeasure(@Id @GeneratedValue var id: Long? = null, var unit: String)
