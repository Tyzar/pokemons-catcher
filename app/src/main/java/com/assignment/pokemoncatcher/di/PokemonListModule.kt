package com.assignment.pokemoncatcher.di

import com.assignment.pokemoncatcher.BuildConfig
import com.assignment.pokemoncatcher.datasources.remote.PokemonApi
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.PokemonApiImpl
import com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.createKtorHttpClient
import com.assignment.pokemoncatcher.implementation.repositories.PokemonRepoImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonListModule {
    companion object {
        @Provides()
        @Singleton
        fun providePokemonApiImpl(): PokemonApiImpl {
            val httpClient =
                createKtorHttpClient(
                    BuildConfig.POKE_API_BASE_URL
                )
            return PokemonApiImpl(
                httpClient
            )
        }

        @Provides
        fun providePokemonRepoImpl(
            pokemonApi: PokemonApi
        ): PokemonRepoImpl {
            return PokemonRepoImpl(
                pokemonApi
            )
        }
    }

    @Binds
    @Singleton
    abstract fun providePokemonApi(
        pokemonApiImpl: PokemonApiImpl
    ): PokemonApi

    @Binds
    abstract fun providePokemonRepo(
        pokemonRepoImpl: PokemonRepoImpl
    ): PokemonRepository
}