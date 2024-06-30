package com.assignment.pokemoncatcher.implementation.datasources.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.assignment.pokemoncatcher.implementation.datasources.local.room.mypokemon_localdata.MyPokemonDao
import com.assignment.pokemoncatcher.implementation.datasources.local.room.pokemon_localdata.PokemonDao
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.TableMyPokemon
import com.assignment.pokemoncatcher.implementation.datasources.local.room.tables.TablePokemon

@Database(
    version = 1,
    entities = [TablePokemon::class, TableMyPokemon::class]
)
abstract class AppDb : RoomDatabase() {
    abstract fun myPokemonDao(): MyPokemonDao

    abstract fun pokemonDao(): PokemonDao
}