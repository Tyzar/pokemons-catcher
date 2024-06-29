package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses

import kotlinx.serialization.Serializable

@Serializable
data class RenamePokemonResponse(
    val status: Boolean,
    val message: String?,
    val data: RenamePokemonData?
) {
    @Serializable
    data class RenamePokemonData(
        val baseName: String?,
        val version: Int?
    )
}