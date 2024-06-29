package com.assignment.pokemoncatcher.datasources.remote.pokemonapi

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.requests.getpokemondetail.GetPokemonDetailRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.requests.getpokemonlist.GetPokemonListRequest
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemondetail.GetPokemonDetailResponse
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemonlist.GetPokemonListResponse

interface PokemonApi {
    suspend fun getPokemonList(request: GetPokemonListRequest): Either<AppError, GetPokemonListResponse>
    suspend fun getPokemonDetail(request: GetPokemonDetailRequest): Either<AppError, GetPokemonDetailResponse>
}