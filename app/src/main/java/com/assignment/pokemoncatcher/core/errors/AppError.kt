package com.assignment.pokemoncatcher.core.errors

open class AppError(open val errMsg: String)

data class NetworkError(override val errMsg: String) :
    AppError(errMsg)