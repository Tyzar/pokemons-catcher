package com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.requests.RenamePokemonRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.CatchPokemonResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.ReleasePokemonResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.RenamePokemonResponse

interface PokemonUtilApi {
    suspend fun catchPokemon(): Either<AppError, CatchPokemonResponse>
    suspend fun releasePokemon(): Either<AppError, ReleasePokemonResponse>
    suspend fun renamePokemon(request: RenamePokemonRequest): Either<AppError, RenamePokemonResponse>
}