package com.assignment.pokemoncatcher.domain.usecases

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonUtilRepository
import javax.inject.Inject

class RenamePokemon @Inject constructor(
    private val pokemonUtilRepo: PokemonUtilRepository,
    private val myPokemonsRepo: MyPokemonsRepository
) {
    suspend fun execute(myPokemon: MyPokemon): Either<AppError, Unit> {
        if (myPokemon.nickname.isEmpty()) {
            return Either.left(
                AppError(
                    "Must give a nickname first before rename"
                )
            )
        }

        val baseName =
            if (!myPokemon.nickname.contains(
                    "-"
                ) && myPokemon.renameCount == 0
            ) {
                myPokemon.nickname
            } else {
                val splittedName =
                    myPokemon.nickname.trim()
                        .split("-")
                splittedName[0]
            }
        val renameCount =
            myPokemon.renameCount + 1

        val renameResult =
            pokemonUtilRepo.renamePokemon(
                basename = baseName,
                renameCount = renameCount
            )
        return when (renameResult) {
            is Either.left -> Either.left(
                renameResult.value
            )

            is Either.right -> {
                val newNickname =
                    renameResult.value
                val updMyPokemon =
                    myPokemon.copy(
                        nickname = newNickname,
                        renameCount = renameCount
                    )
                return myPokemonsRepo.updateMyPokemon(
                    updMyPokemon
                )
            }
        }

    }
}