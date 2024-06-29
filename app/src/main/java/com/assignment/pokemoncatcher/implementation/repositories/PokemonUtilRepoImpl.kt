package com.assignment.pokemoncatcher.implementation.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.PokemonUtilApi
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.requests.RenamePokemonRequest
import com.assignment.pokemoncatcher.domain.repositories.PokemonUtilRepository
import javax.inject.Inject

class PokemonUtilRepoImpl @Inject constructor(
    private val pokemonUtilApi: PokemonUtilApi
) : PokemonUtilRepository {
    override suspend fun catchPokemon(): Either<AppError, Boolean> {
        val catchResult =
            pokemonUtilApi.catchPokemon()
        return when (catchResult) {
            is Either.left -> Either.left(
                catchResult.value
            )

            is Either.right -> {
                val respData =
                    catchResult.value
                val isHit =
                    (respData.data?.isHit
                        ?: 0) == 1
                return Either.right(
                    isHit
                )
            }
        }
    }

    override suspend fun releasePokemon(): Either<AppError, Int> {
        val releaseResult =
            pokemonUtilApi.releasePokemon()
        return when (releaseResult) {
            is Either.left -> Either.left(
                releaseResult.value
            )

            is Either.right -> {
                val respData =
                    releaseResult.value
                val releaseNum =
                    respData.data?.releaseNum
                        ?: 0
                return Either.right(
                    releaseNum
                )
            }
        }
    }

    override suspend fun renamePokemon(
        basename: String,
        renameCount: Int
    ): Either<AppError, String> {
        val request =
            RenamePokemonRequest(
                basename,
                renameCount
            )
        val renameResult =
            pokemonUtilApi.renamePokemon(
                request
            )
        return when (renameResult) {
            is Either.left -> Either.left(
                renameResult.value
            )

            is Either.right -> {
                val respData =
                    renameResult.value
                val version =
                    respData.data?.version
                        ?: 0
                return Either.right("${basename}-$version")
            }
        }
    }
}