package com.assignment.pokemoncatcher.implementation.repositories

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.local.mypokemon_localdata.MyPokemonLocalData
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import javax.inject.Inject

class MyPokemonsRepoImpl @Inject constructor(
    private val myPokemonLocalData: MyPokemonLocalData
) : MyPokemonsRepository {
    override suspend fun addPokemon(
        myPokemon: MyPokemon
    ): Either<AppError, Unit> {
        return myPokemonLocalData.upsert(
            myPokemon
        )
    }

    override suspend fun getAll(): Either<AppError, List<MyPokemon>> {
        return myPokemonLocalData.get()
    }

    override suspend fun removePokemon(
        id: Int
    ): Either<AppError, Unit> {
        return myPokemonLocalData.delete(
            id
        )
    }

    override suspend fun get(id: Int): MyPokemon? {
        val result =
            myPokemonLocalData.get(id)
        return when (result) {
            is Either.left -> null
            is Either.right -> result.value
        }
    }

    override suspend fun updateMyPokemon(
        myPokemon: MyPokemon
    ): Either<AppError, Unit> {
        return myPokemonLocalData.upsert(
            myPokemon
        )
    }
}