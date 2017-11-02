package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.ManyToMany
import javax.persistence.GenerationType

@Entity
data class Category (@field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
					 var id: Long = -1,
					 
					 var description: String = "",
					 
					 @ManyToMany(mappedBy = "categories") var recipes: Set<Recipe> = emptySet())