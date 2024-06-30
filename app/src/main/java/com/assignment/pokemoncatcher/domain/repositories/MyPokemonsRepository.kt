package com.assignment.pokemoncatcher.domain.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.entities.Pokemon

interface MyPokemonsRepository {
    suspend fun addPokemon(myPokemon: MyPokemon): Either<AppError, Unit>
    suspend fun getAll(): Either<AppError, List<MyPokemon>>
    suspend fun removePokemon(myPokemon: MyPokemon): Either<AppError, Unit>
    suspend fun get(id: Int): MyPokemon?
    suspend fun updateMyPokemon(
        myPokemon: MyPokemon
    ): Either<AppError, Unit>
}
