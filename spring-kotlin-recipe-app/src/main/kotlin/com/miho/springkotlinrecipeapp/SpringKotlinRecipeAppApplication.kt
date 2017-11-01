package com.miho.springkotlinrecipeapp

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
open class SpringKotlinRecipeAppApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringKotlinRecipeAppApplication::class.java, *args)
}
