package com.miho.springkotlinrecipeapp.domain

import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.persistence.OneToOne

@Entity
class Ingredient(var description: String = "",
				  					  
				 var amount: BigDecimal = BigDecimal.ZERO,
				 
				  @OneToOne
				 var unitOfMeasure: UnitOfMeasure? = null,
				 
				 @ManyToOne
				 var recipe: Recipe? = null,				  
				  
				 @field: [Id  GeneratedValue(strategy = GenerationType.IDENTITY)]
				 var id: Long = -1){
	
	
	override fun toString() = "$amount ${unitOfMeasure?.unit} of $description"
					  
}
