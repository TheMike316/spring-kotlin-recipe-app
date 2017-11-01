package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.ManyToMany

@Entity
data class Category (@Id @GeneratedValue var id: Long? = null, var description: String,
					 @ManyToMany(mappedBy = "categories") var recipes: Set<Recipe>)