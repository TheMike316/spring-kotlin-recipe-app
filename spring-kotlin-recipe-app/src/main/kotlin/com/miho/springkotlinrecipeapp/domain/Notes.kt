package com.miho.springkotlinrecipeapp.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Entity
import javax.persistence.Lob

@Entity
data class Notes(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null, @OneToOne var recipe: Recipe, @Lob var recipeNotes: String)