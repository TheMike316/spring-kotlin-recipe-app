package com.miho.springkotlinrecipeapp.domain

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Entity
import javax.persistence.Lob

@Entity
data class Notes(@field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
				 var id: Long = -1,
				 
				 @OneToOne
				 var recipe: Recipe = Recipe(),
				 
				 @Lob
				 var recipeNotes: String = "")