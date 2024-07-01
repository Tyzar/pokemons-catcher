package com.assignment.pokemoncatcher.implementation.datasources.local.room.pokemon_localdata

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.DebugLog
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.datasources.local.pokemon_localdata.PokemonLocalData
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.implementation.datasources.local.room.db.AppDb
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.toDomain
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.toTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonLocalDataImpl @Inject constructor(
    private val appDb: AppDb
) : PokemonLocalData {
    private val pokemonDao =
        appDb.pokemonDao()

    override suspend fun upsert(pokemon: Pokemon): Either<AppError, Unit> =
        withContext(Dispatchers.IO) {
            try {
                pokemonDao.upsert(
                    pokemon.toTable()
                )
                return@withContext Either.right(
                    Unit
                )
            } catch (e: Exception) {
                return@withContext Either.left(
                    AppError("Failed to insert or update pokemon data")
                )
            }
        }

    override suspend fun batchUpsert(
        pokemons: List<Pokemon>
    ): Either<AppError, Unit> =
        withContext(Dispatchers.IO) {
            try {
                pokemonDao.batchUpsert(
                    pokemons.map { it.toTable() })
                DebugLog.log(msg = "Insert pokemons to local success")
                return@withContext Either.right(
                    Unit
                )
            } catch (e: Exception) {
                DebugLog.log(
                    v = DebugLog.Verbose.ERROR,
                    msg = "Insert pokemons to local error"
                )
                return@withContext Either.left(
                    AppError("Failed to insert or update pokemon data")
                )
            }
        }

    override suspend fun get(id: Int): Pokemon? =
        withContext(Dispatchers.IO) {
            try {
                val tPokemon =
                    pokemonDao.get(id)

                return@withContext tPokemon?.toDomain()
            } catch (e: Exception) {
                return@withContext null
            }
        }
}