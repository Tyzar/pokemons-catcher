package com.assignment.pokemoncatcher.implementation.datasources.local.room.mypokemon_localdata

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.local.mypokemon_localdata.MyPokemonLocalData
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.implementation.datasources.local.room.db.AppDb
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.toDomain
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.toTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MyPokemonLocalDataImpl @Inject constructor(
    private val appDb: AppDb
) :
    MyPokemonLocalData {

    private val myPokemonDao =
        appDb.myPokemonDao()

    override suspend fun upsert(
        myPokemon: MyPokemon
    ): Either<AppError, Unit> =
        withContext(Dispatchers.IO) {
            try {
                myPokemonDao.upsert(
                    myPokemon.toTable()
                )
                return@withContext Either.right(
                    Unit
                )
            } catch (e: Exception) {
                return@withContext Either.left(
                    AppError("Failed to insert or update my pokemon data")
                )
            }
        }

    override suspend fun delete(
        myPokemon: MyPokemon
    ): Either<AppError, Unit> =
        withContext(Dispatchers.IO) {
            try {
                myPokemonDao.delete(
                    myPokemon.toTable()
                )
                return@withContext Either.right(
                    Unit
                )
            } catch (e: Exception) {
                return@withContext Either.left(
                    AppError("Failed to delete my pokemon")
                )
            }
        }

    override suspend fun get(): Either<AppError, List<MyPokemon>> =
        withContext(Dispatchers.IO) {
            try {
                val result =
                    myPokemonDao.get()
                val pokemons =
                    result.map {
                        MyPokemon(
                            pokemon = it.value.toDomain(),
                            nickname = it.key.nickname
                                ?: "",
                            renameCount = it.key.renameCount
                        )
                    }
                return@withContext Either.right(
                    pokemons
                )
            } catch (e: Exception) {
                return@withContext Either.left(
                    AppError("Failed to get my pokemon data")
                )
            }
        }

    override suspend fun get(id: Int): Either<AppError, MyPokemon?> =
        withContext(Dispatchers.IO) {
            try {
                val result =
                    myPokemonDao.get(id)
                if (result.isEmpty()) {
                    return@withContext Either.right(
                        null
                    )
                }

                val entry =
                    result.entries.iterator()
                        .next()
                return@withContext Either.right(
                    MyPokemon(
                        pokemon = entry.value.toDomain(),
                        nickname = entry.key.nickname
                            ?: "",
                        renameCount = entry.key.renameCount
                    )
                )
            } catch (e: Exception) {
                return@withContext Either.left(
                    AppError("Failed to get my pokemon data")
                )
            }
        }
}