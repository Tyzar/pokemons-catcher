package com.assignment.pokemoncatcher.di

import android.content.Context
import androidx.room.Room
import com.assignment.pokemoncatcher.datasources.local.mypokemon_localdata.MyPokemonLocalData
import com.assignment.pokemoncatcher.datasources.local.pokemon_localdata.PokemonLocalData
import com.assignment.pokemoncatcher.implementation.datasources.local.room.db.AppDb
import com.assignment.pokemoncatcher.implementation.datasources.local.room.mypokemon_localdata.MyPokemonLocalDataImpl
import com.assignment.pokemoncatcher.implementation.datasources.local.room.pokemon_localdata.PokemonLocalDataImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourcesModule {
    companion object {
        @Provides
        @Singleton
        fun provideAppDb(@ApplicationContext context: Context): AppDb {
            return Room.databaseBuilder(
                context,
                AppDb::class.java,
                "pokemon-catcher-db"
            ).build()
        }

        @Provides
        @Singleton
        fun providePokemonLocalDataImpl(
            appDb: AppDb
        ): PokemonLocalDataImpl {
            return PokemonLocalDataImpl(
                appDb
            )
        }

        @Provides
        @Singleton
        fun provideMyPokemonLocalDataImpl(
            appDb: AppDb
        ): MyPokemonLocalDataImpl {
            return MyPokemonLocalDataImpl(
                appDb
            )
        }
    }

    @Binds
    @Singleton
    abstract fun providePokemonLocalData(
        pokemonLocalDataImpl: PokemonLocalDataImpl
    ): PokemonLocalData

    @Binds
    @Singleton
    abstract fun provideMyPokemonLocalData(
        myPokemonLocalDataImpl: MyPokemonLocalDataImpl
    ): MyPokemonLocalData
}