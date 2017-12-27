package com.miho.springkotlinrecipeapp.exceptions

import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException (message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause)