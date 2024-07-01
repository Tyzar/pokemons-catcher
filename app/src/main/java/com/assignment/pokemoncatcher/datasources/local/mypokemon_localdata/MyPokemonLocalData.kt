package com.assignment.pokemoncatcher.datasources.local.mypokemon_localdata

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon

interface MyPokemonLocalData {
    suspend fun upsert(myPokemon: MyPokemon): Either<AppError, Unit>
    suspend fun delete(id: Int): Either<AppError, Unit>
    suspend fun get(): Either<AppError, List<MyPokemon>>
    suspend fun get(id: Int): Either<AppError, MyPokemon?>
}