package com.miho.springkotlinrecipeapp.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Entity
import javax.persistence.Lob

@Entity
class Notes(@OneToOne
				 var recipe: Recipe? = null,
				 
				 @Lob
				 var recipeNotes: String = "",
				 
				 @field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
				 var id: Long = -1)