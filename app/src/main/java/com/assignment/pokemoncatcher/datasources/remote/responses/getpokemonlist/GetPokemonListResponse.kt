package com.assignment.pokemoncatcher.datasources.remote.responses.getpokemonlist

import com.assignment.pokemoncatcher.domain.entities.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonListResponse(
    val next: String,
    val previous: String,
    val results: List<ResultItem>
) {
    @Serializable
    data class ResultItem(
        val name: String,
        val url: String
    )
}