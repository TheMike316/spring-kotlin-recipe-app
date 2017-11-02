package com.miho.springkotlinrecipeapp.repositories

import org.springframework.data.repository.CrudRepository
import com.miho.springkotlinrecipeapp.domain.Category

interface CategoryRepository: CrudRepository<Category, Long>