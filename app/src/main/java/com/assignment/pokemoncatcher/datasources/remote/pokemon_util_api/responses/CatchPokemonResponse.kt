package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses

import kotlinx.serialization.Serializable

data class CatchPokemonResponse(
    val status: Boolean,
    val message: String?,
    val data: CatchPokemonData?
) {
    @Serializable
    data class CatchPokemonData(val isHit: Int?)
}