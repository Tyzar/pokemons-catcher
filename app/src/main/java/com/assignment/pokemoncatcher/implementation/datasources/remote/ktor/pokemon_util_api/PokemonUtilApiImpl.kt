package com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.pokemon_util_api

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.errors.NetworkError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.PokemonUtilApi
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.requests.RenamePokemonRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.CatchPokemonResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.ReleasePokemonResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.responses.RenamePokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class PokemonUtilApiImpl @Inject constructor(
    private val httpClient: HttpClient
) :
    PokemonUtilApi {

    companion object {
        private const val POST_CATCH_POKEMON =
            "/poke/catch"
        private const val POST_RELEASE_POKEMON =
            "/poke/release"
        private const val POST_RENAME_POKEMON =
            "/poke/rename"
    }

    override suspend fun catchPokemon(): Either<AppError, CatchPokemonResponse> =
        withContext(Dispatchers.IO) {
            try {
                val resp =
                    httpClient.post(
                        POST_CATCH_POKEMON
                    )
                        .body<CatchPokemonResponse>()
                return@withContext Either.right(
                    resp
                )
            } catch (e: Exception) {
                if (e is UnknownHostException || e is SocketTimeoutException) {
                    return@withContext Either.left(
                        NetworkError("Please check your connection")
                    )
                }
                return@withContext Either.left(
                    AppError("Error on catch pokemon")
                )
            }
        }

    override suspend fun releasePokemon(): Either<AppError, ReleasePokemonResponse> =
        withContext(Dispatchers.IO) {
            try {
                val resp =
                    httpClient.post(
                        POST_RELEASE_POKEMON
                    )
                        .body<ReleasePokemonResponse>()
                return@withContext Either.right(
                    resp
                )
            } catch (e: Exception) {
                if (e is UnknownHostException || e is SocketTimeoutException) {
                    return@withContext Either.left(
                        NetworkError("Please check your connection")
                    )
                }
                return@withContext Either.left(
                    AppError("Error on release pokemon")
                )
            }
        }

    override suspend fun renamePokemon(
        request: RenamePokemonRequest
    ): Either<AppError, RenamePokemonResponse> =
        withContext(Dispatchers.IO) {
            try {
                val resp =
                    httpClient.post(
                        POST_RENAME_POKEMON
                    ) {
                        contentType(
                            ContentType.Application.Json
                        )
                        setBody(request)
                    }
                        .body<RenamePokemonResponse>()
                return@withContext Either.right(
                    resp
                )
            } catch (e: Exception) {
                if (e is UnknownHostException || e is SocketTimeoutException) {
                    return@withContext Either.left(
                        NetworkError("Please check your connection")
                    )
                }
                return@withContext Either.left(
                    AppError("Error on rename pokemon")
                )
            }
        }
}