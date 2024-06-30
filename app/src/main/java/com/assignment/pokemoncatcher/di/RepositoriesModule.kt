package com.assignment.pokemoncatcher.di

import com.assignment.pokemoncatcher.datasources.local.mypokemon_localdata.MyPokemonLocalData
import com.assignment.pokemoncatcher.datasources.local.pokemon_localdata.PokemonLocalData
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.PokemonApi
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import com.assignment.pokemoncatcher.implementation.repositories.MyPokemonsRepoImpl
import com.assignment.pokemoncatcher.implementation.repositories.PokemonRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    companion object {
        @Provides
        fun providePokemonRepoImpl(
            pokemonApi: PokemonApi,
            pokemonLocalData: PokemonLocalData
        ): PokemonRepoImpl {
            return PokemonRepoImpl(
                pokemonApi,
                pokemonLocalData
            )
        }

        @Provides
        fun provideMyPokemonRepoImpl(
            myPokemonLocalData: MyPokemonLocalData
        ): MyPokemonsRepoImpl {
            return MyPokemonsRepoImpl(
                myPokemonLocalData
            )
        }
    }

    @Binds
    abstract fun providePokemonRepo(
        pokemonRepoImpl: PokemonRepoImpl
    ): PokemonRepository

    @Binds
    abstract fun provideMyPokemonRepo(
        myPokemonsRepoImpl: MyPokemonsRepoImpl
    ): MyPokemonsRepository
}