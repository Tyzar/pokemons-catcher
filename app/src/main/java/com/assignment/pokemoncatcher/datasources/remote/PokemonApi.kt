package com.assignment.pokemoncatcher.datasources.remote

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.requests.getpokemondetail.GetPokemonDetailRequest
import com.assignment.pokemoncatcher.datasources.remote.requests.getpokemonlist.GetPokemonListRequest
import com.assignment.pokemoncatcher.datasources.remote.responses.getpokemondetail.GetPokemonDetailResponse
import com.assignment.pokemoncatcher.datasources.remote.responses.getpokemonlist.GetPokemonListResponse

interface PokemonApi {
    suspend fun getPokemonList(request: GetPokemonListRequest): Either<AppError, GetPokemonListResponse>
    suspend fun getPokemonDetail(request: GetPokemonDetailRequest): Either<AppError, GetPokemonDetailResponse>
}