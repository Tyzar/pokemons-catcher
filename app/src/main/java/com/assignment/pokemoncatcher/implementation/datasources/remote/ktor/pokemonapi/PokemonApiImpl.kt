package com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.pokemonapi

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.errors.NetworkError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.PokemonApi
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.requests.getpokemondetail.GetPokemonDetailRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.requests.getpokemonlist.GetPokemonListRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemondetail.GetPokemonDetailResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemonlist.GetPokemonListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class PokemonApiImpl @Inject constructor(
    private val httpClient: HttpClient
) :
    PokemonApi {
    companion object {
        const val GET_POKEMON =
            "/api/v2/pokemon"
    }

    override suspend fun getPokemonList(
        request: GetPokemonListRequest
    ): Either<AppError, GetPokemonListResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response =
                    httpClient.get(
                        GET_POKEMON
                    ) {
                        url {
                            parameters.append(
                                "limit",
                                request.limit.toString()
                            )
                            parameters.append(
                                "offset",
                                request.offset.toString()
                            )
                        }
                    }
                        .body<GetPokemonListResponse>()
                return@withContext Either.right(
                    response
                )
            } catch (e: Exception) {
                if (e is UnknownHostException || e is SocketTimeoutException) {
                    return@withContext Either.left(
                        NetworkError("Please check your connection")
                    )
                }
                return@withContext Either.left(
                    AppError("Failed to get pokemon list")
                )
            }
        }

    override suspend fun getPokemonDetail(
        request: GetPokemonDetailRequest
    ) = withContext(Dispatchers.IO) {
        try {
            val response =
                httpClient.get(request.url)
                    .body<GetPokemonDetailResponse>()
            return@withContext Either.right(
                response
            )
        } catch (e: Exception) {
            if (e is UnknownHostException || e is SocketTimeoutException) {
                return@withContext Either.left(
                    NetworkError("Please check your connection")
                )
            }
            return@withContext Either.left(
                AppError("Failed to get pokemon detail")
            )
        }
    }
}