package com.assignment.pokemoncatcher.domain.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either

interface PokemonUtilRepository {
    suspend fun catchPokemon(): Either<AppError, Boolean>
    suspend fun releasePokemon(): Either<AppError, Int>
    suspend fun renamePokemon(
        basename: String,
        renameCount: Int
    ): Either<AppError, String>
}