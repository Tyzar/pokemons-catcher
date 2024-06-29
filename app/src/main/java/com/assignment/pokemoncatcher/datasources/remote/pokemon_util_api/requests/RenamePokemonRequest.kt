package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.requests

import kotlinx.serialization.Serializable

@Serializable
data class RenamePokemonRequest(
    val name: String,
    val updateCount: Int
)