package com.assignment.pokemoncatcher.domain.usecases

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonUtilRepository
import javax.inject.Inject

class CatchPokemon @Inject constructor(
    private val pokemonUtilRepo: PokemonUtilRepository,
    private val myPokemonsRepo: MyPokemonsRepository
) {
    suspend fun execute(pokemon: Pokemon): Either<AppError, Boolean> {
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
                        pokemon
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