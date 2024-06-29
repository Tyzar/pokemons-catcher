package com.assignment.pokemoncatcher.domain.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.FetchFilter
import com.assignment.pokemoncatcher.domain.entities.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(filter: FetchFilter): Either<AppError, List<Pokemon>>
}
