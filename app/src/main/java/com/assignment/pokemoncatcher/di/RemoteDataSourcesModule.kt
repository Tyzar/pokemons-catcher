package com.assignment.pokemoncatcher.di

import com.assignment.pokemoncatcher.BuildConfig
import com.assignment.pokemoncatcher.datasources.remote.pokemon_util_api.PokemonUtilApi
import com.assignment.pokemoncatcher.datasources.remote.pokemonapi.PokemonApi
import com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.createKtorHttpClient
import com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.pokemon_util_api.PokemonUtilApiImpl
import com.assignment.pokemoncatcher.implementation.datasources.remote.ktor.pokemonapi.PokemonApiImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourcesModule {
    companion object {
        @Provides
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
        @Singleton
        fun providePokemonUtilApiImpl(): PokemonUtilApiImpl {
            val httpClient =
                createKtorHttpClient(
                    BuildConfig.POKE_UTIL_BASE_URL
                )
            return PokemonUtilApiImpl(
                httpClient
            )
        }
    }

    @Binds
    @Singleton
    abstract fun providePokemonApi(
        pokemonApiImpl: PokemonApiImpl
    ): PokemonApi

    @Binds
    @Singleton
    abstract fun providePokemonUtilApi(
        pokemonUtilApiImpl: PokemonUtilApiImpl
    ): PokemonUtilApi
}