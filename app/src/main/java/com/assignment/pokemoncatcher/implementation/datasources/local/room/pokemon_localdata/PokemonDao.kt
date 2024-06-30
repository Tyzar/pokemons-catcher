package com.assignment.pokemoncatcher.implementation.datasources.local.room.pokemon_localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.TablePokemon

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(tPokemon: TablePokemon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun batchUpsert(tPokemons: List<TablePokemon>)

    @Query("SELECT * FROM pokemon WHERE id = :id LIMIT 1")
    fun get(id: Int): TablePokemon?
}
