package com.miho.springkotlinrecipeapp.bootstrap

import com.miho.springkotlinrecipeapp.domain.Category
import com.miho.springkotlinrecipeapp.domain.UnitOfMeasure
import com.miho.springkotlinrecipeapp.repositories.CategoryRepository
import com.miho.springkotlinrecipeapp.repositories.UnitOfMeasureRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev", "prod")
class BootstrapMariaDB(private val categoryRepository: CategoryRepository,
                       private val unitOfMeasureRepository: UnitOfMeasureRepository) : InitializingBean {

    override fun afterPropertiesSet() {
        if (categoryRepository.count() == 0L) {
            loadCategories()
        }

        if (unitOfMeasureRepository.count() == 0L) {
            loadUom()
        }
    }

    private fun loadCategories() {
        arrayOf(Category("American"), Category("Italian"),
                Category("Mexican"), Category("Austrian"),
                Category("Fast Food")).forEach { categoryRepository.save(it) }
    }

    private fun loadUom() {
        arrayOf(UnitOfMeasure("Teaspoon"), UnitOfMeasure("Tablespoon"), UnitOfMeasure("Cup"),
                UnitOfMeasure("Pinch"), UnitOfMeasure("Ounce"), UnitOfMeasure("Clove"),
                UnitOfMeasure("Pint"), UnitOfMeasure("Dash"), UnitOfMeasure("Each"))
                .forEach { unitOfMeasureRepository.save(it) }
    }
}