package com.assignment.pokemoncatcher.implementation.datasources.local.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.pokemoncatcher.domain.entities.MyPokemon

@Entity(tableName = "my_pokemon")
data class TableMyPokemon(
    @PrimaryKey
    val pokemonId: Int,
    val nickname: String?,
    val renameCount: Int
)

fun MyPokemon.toTable(): TableMyPokemon {
    return TableMyPokemon(
        pokemonId = pokemon.id,
        nickname = nickname,
        renameCount = renameCount
    )
}