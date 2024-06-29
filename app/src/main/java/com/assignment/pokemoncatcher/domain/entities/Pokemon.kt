package com.assignment.pokemoncatcher.domain.entities

data class Pokemon(
    val id: Int,
    val name: String,
    val artwork: Artwork? = null,
    val stats: List<Stat> = emptyList(),
    val types: List<PokemonType> = emptyList(),
    val moves: List<Move> = emptyList()
) {
    data class Artwork(val url: String?)

    data class Stat(
        val statName: String,
        val statValue: Int
    )

    data class PokemonType(val typeName: String)

    data class Move(val moveName: String)
}


