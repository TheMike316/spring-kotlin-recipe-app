package com.miho.springkotlinrecipeapp.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import com.miho.springkotlinrecipeapp.repositories.CategoryRepository
import com.miho.springkotlinrecipeapp.repositories.UnitOfMeasureRepository

@Controller
class IndexController() {
	
	@RequestMapping("", "/", "/index")
	fun getIndexPage() = "index"
	
}