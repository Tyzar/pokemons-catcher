package com.assignment.pokemoncatcher.domain.entities

data class FetchFilter(
    val resultLimit: Int = 20,
    val resultOffset: Int = 0
)
