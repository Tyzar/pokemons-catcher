package com.assignment.pokemoncatcher.implementation.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository

class MyPokemonsRepoImpl: MyPokemonsRepository {
    override suspend fun addPokemon(
        pokemon: Pokemon
    ): Either<AppError, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Either<AppError, List<MyPokemon>> {
        TODO("Not yet implemented")
    }

    override suspend fun removePokemon(
        pokemon: Pokemon
    ): Either<AppError, Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun get(id: Int): MyPokemon? {
        TODO("Not yet implemented")
    }

    override suspend fun updateMyPokemon(
        pokemon: MyPokemon
    ): Either<AppError, Unit> {
        TODO("Not yet implemented")
    }
}