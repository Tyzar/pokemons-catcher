package com.assignment.pokemoncatcher.implementation.datasources.local.room.mypokemon_localdata

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.TableMyPokemon
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.TablePokemon

@Dao
interface MyPokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(tMyPokemon: TableMyPokemon)

    @Delete
    fun delete(tMyPokemon: TableMyPokemon)

    @Query(
        "SELECT * FROM my_pokemon " +
                "JOIN pokemon ON my_pokemon.pokemonId = pokemon.id"
    )
    fun get(): Map<TableMyPokemon, TablePokemon>

    @Query(
        "SELECT * FROM my_pokemon " +
                "JOIN pokemon ON my_pokemon.pokemonId = pokemon.id " +
                "WHERE my_pokemon.pokemonId = :id LIMIT 1"
    )
    fun get(id: Int): Map<TableMyPokemon, TablePokemon>
}
