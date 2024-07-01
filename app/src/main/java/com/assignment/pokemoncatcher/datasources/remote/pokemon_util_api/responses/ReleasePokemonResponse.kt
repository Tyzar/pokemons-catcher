package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses

import kotlinx.serialization.Serializable

@Serializable
data class ReleasePokemonResponse(
    val status: Boolean,
    val message: String? = null,
    val data: ReleasePokemonData? = null
) {
    @Serializable
    data class ReleasePokemonData(val releaseNum: Int?)
}