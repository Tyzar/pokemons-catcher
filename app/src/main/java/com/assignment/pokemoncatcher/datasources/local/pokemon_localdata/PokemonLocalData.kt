package com.assignment.pokemoncatcher.datasources.local.pokemon_localdata

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.Pokemon

interface PokemonLocalData {
    suspend fun upsert(pokemon: Pokemon): Either<AppError, Unit>
    suspend fun batchUpsert(pokemons: List<Pokemon>): Either<AppError, Unit>
    suspend fun get(id: Int): Pokemon?
}