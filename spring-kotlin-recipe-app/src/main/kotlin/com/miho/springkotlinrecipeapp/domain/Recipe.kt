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
data class Recipe(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
				   var description: String, var prepTime: Int, var cookTime: Int, var servings: Int,
				   var source: String, var url: String, var directions: String,
				   @Lob var image: Array<Byte>,
				   @OneToOne(cascade = arrayOf(CascadeType.ALL)) var notes: Notes,
				   @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "recipe") var ingredients: Set<Ingredient>,
				   @Enumerated(EnumType.STRING) var difficulty: Difficulty,
				   @ManyToMany
				   @JoinTable(name = "recipe_category", joinColumns = arrayOf(JoinColumn(name = "recipe_id")),
						   inverseJoinColumns = arrayOf(JoinColumn(name = "category_id"))) var categories: Set<Category>) 