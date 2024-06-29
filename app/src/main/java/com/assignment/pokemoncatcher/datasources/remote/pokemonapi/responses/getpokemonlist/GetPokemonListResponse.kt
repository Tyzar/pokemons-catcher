package com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemonlist

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