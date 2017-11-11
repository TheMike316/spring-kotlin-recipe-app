package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.ManyToMany
import javax.persistence.GenerationType

@Entity
class Category (var description: String = "",
					 
					 @ManyToMany(mappedBy = "categories")
					 val recipes: MutableSet<Recipe> = mutableSetOf(),
					 
					 @field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
					 var id: Long = -1)