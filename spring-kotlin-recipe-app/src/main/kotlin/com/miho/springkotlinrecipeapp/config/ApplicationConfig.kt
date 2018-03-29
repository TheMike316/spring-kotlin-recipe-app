package com.miho.springkotlinrecipeapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer

@Configuration
class ApplicationConfig {

    @Bean
    fun placeholderConfigurer() = PropertySourcesPlaceholderConfigurer()
}