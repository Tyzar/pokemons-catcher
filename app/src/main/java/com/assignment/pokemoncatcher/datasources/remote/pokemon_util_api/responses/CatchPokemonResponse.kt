package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses

import kotlinx.serialization.Serializable

@Serializable
data class CatchPokemonResponse(
    val status: Boolean,
    val message: String? = null,
    val data: CatchPokemonData? = null
) {
    @Serializable
    data class CatchPokemonData(val isHit: Int?)
}