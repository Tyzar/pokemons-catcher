package com.assignment.pokemoncatcher.domain.usecases

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonUtilRepository
import javax.inject.Inject

class CatchPokemon @Inject constructor(
    private val pokemonUtilRepo: PokemonUtilRepository,
    private val myPokemonsRepo: MyPokemonsRepository,
    private val pokemonRepo: PokemonRepository
) {
    suspend fun execute(id: Int): Either<AppError, Boolean> {

        val pokemon =
            pokemonRepo.getPokemonDetail(
                id
            )
        if (pokemon == null) {
            return Either.left(
                AppError("Pokemon data not found")
            )
        }

        val catchResult =
            pokemonUtilRepo.catchPokemon()
        return when (catchResult) {
            is Either.left -> Either.left(
                catchResult.value
            )

            is Either.right -> {
                if (!catchResult.value) {
                    return Either.right(
                        false
                    )
                }

                val addResult =
                    myPokemonsRepo.addPokemon(
                        MyPokemon(
                            pokemon = pokemon,
                            nickname = "",
                            renameCount = 0
                        )
                    )
                when (addResult) {
                    is Either.left -> Either.left(
                        addResult.value
                    )

                    is Either.right -> Either.right(
                        true
                    )
                }
            }
        }
    }
}