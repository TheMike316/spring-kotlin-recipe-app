package com.miho.springkotlinrecipeapp.domain

import javax.persistence.OneToOne
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Lob
import javax.persistence.OneToMany
import javax.persistence.Enumerated
import javax.persistence.EnumType
import javax.persistence.ManyToMany
import javax.persistence.JoinTable
import javax.persistence.JoinColumn

@Entity
class Recipe(var description: String = "",
				  
				  var prepTime: Int = 0,
				  
				  var cookTime: Int = 0,
				  
				  var servings: Int = 0,
				  
				  var source: String = "",
				  
				  var url: String = "",
				  
			      @Lob
				  var directions: String = "",
				  
				  @Lob
				  var image: Array<Byte> = emptyArray(),
				  
				  @OneToOne(cascade = arrayOf(CascadeType.ALL))
				  var notes: Notes? = null,
				  
				  @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "recipe")
				  val ingredients: MutableSet<Ingredient> = mutableSetOf(),
				  
				  @Enumerated(EnumType.STRING)
				  var difficulty: Difficulty = Difficulty.EASY,
				  
				  @ManyToMany
				  @JoinTable(name = "recipe_category", joinColumns = arrayOf(JoinColumn(name = "recipe_id")),
						   inverseJoinColumns = arrayOf(JoinColumn(name = "category_id")))
				  val categories: MutableSet<Category> = mutableSetOf(),
				  
				  @field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
				  var id: Long = -1) 