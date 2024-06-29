package com.assignment.pokemoncatcher.domain.usecases

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonUtilRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReleasePokemon @Inject constructor(
    private val pokemonUtilRepo: PokemonUtilRepository,
    private val myPokemonsRepo: MyPokemonsRepository
) {
    suspend fun execute(pokemon: Pokemon): Either<AppError, Boolean> {
        val releaseNumberResult =
            pokemonUtilRepo.releasePokemon()
        return when (releaseNumberResult) {
            is Either.left -> Either.left(
                releaseNumberResult.value
            )

            is Either.right -> {
                val isPrime =
                    isPrimeNumber(
                        releaseNumberResult.value
                    )
                if (!isPrime) {
                    return Either.right(
                        false
                    )
                }

                myPokemonsRepo.removePokemon(
                    pokemon
                )
                return Either.right(true)
            }
        }
    }

    private suspend fun isPrimeNumber(
        number: Int
    ): Boolean =
        withContext(Dispatchers.Default) {
            if (number <= 1) {
                return@withContext false
            }

            if (number <= 3) {
                return@withContext true
            }

            if (number % 2 == 0 || number % 3 == 0) {
                return@withContext false
            }

            var i = 5
            while (i * i <= number) {
                if (number % i == 0 || number % (i + 2) == 0) {
                    return@withContext false
                }

                i += 6
            }

            return@withContext true
        }
}