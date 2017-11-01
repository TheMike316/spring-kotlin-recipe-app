package com.miho.springkotlinrecipeapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringKotlinRecipeAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringKotlinRecipeAppApplication::class.java, *args)
}
