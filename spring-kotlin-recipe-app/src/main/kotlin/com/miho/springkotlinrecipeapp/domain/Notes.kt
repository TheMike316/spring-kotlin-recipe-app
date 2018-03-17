package com.miho.springkotlinrecipeapp.domain

import javax.persistence.*

@Entity
class Notes(@OneToOne
            var recipe: Recipe? = null,

            @Lob
            var recipeNotes: String = "",

            @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
            var id: Long = -1)