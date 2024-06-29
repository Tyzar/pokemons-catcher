package com.assignment.pokemoncatcher.domain

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.FetchFilter
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import javax.inject.Inject

class GetPokemonList @Inject constructor(
    private val pokemonRepo: PokemonRepository
) {
    suspend fun execute(filter: FetchFilter): Either<AppError, List<Pokemon>> {
        return pokemonRepo.getPokemonList(
            filter
        )
    }
}