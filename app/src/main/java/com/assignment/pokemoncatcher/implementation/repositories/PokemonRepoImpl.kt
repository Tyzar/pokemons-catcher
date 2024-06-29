package com.assignment.pokemoncatcher.implementation.repositories

import android.util.Log
import com.assignment.pokemoncatcher.BuildConfig
import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.PokemonApi
import com.assignment.pokemoncatcher.datasources.remote.requests.getpokemondetail.GetPokemonDetailRequest
import com.assignment.pokemoncatcher.datasources.remote.requests.getpokemonlist.GetPokemonListRequest
import com.assignment.pokemoncatcher.datasources.remote.responses.getpokemondetail.toDomain
import com.assignment.pokemoncatcher.domain.entities.FetchFilter
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import javax.inject.Inject

class PokemonRepoImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {

    override suspend fun getPokemonList(
        filter: FetchFilter
    ): Either<AppError, List<Pokemon>> {
        val request =
            GetPokemonListRequest(
                limit = filter.resultLimit,
                offset = filter.resultOffset
            )
        val apiResult =
            api.getPokemonList(request)
        return when (apiResult) {
            is Either.left -> {
                Either.left(
                    apiResult.value
                )
            }

            is Either.right -> {
                val detailResult =
                    getPokemonDetailBatch(
                        apiResult.value.results.map {
                            it.url
                        }
                    )

                return Either.right(
                    detailResult
                )
            }
        }
    }

    suspend fun getPokemonDetailBatch(
        urls: List<String>
    ): List<Pokemon> {
        val pokemons =
            mutableListOf<Pokemon>()
        for (url in urls) {
            val request =
                GetPokemonDetailRequest(
                    url
                )
            val apiResult =
                api.getPokemonDetail(
                    request
                )
            when (apiResult) {
                is Either.left -> {
                    if (BuildConfig.DEBUG) {
                        Log.e(
                            "Pokemon",
                            apiResult.value.errMsg
                        )
                    }
                    continue
                }

                is Either.right -> {
                    pokemons.add(
                        apiResult.value.toDomain()
                    )
                    if (BuildConfig.DEBUG) {
                        Log.d(
                            "Pokemon",
                            "Success fetch detail pokemon ${apiResult.value.name}"
                        )
                    }
                }
            }
        }

        return pokemons
    }
}
