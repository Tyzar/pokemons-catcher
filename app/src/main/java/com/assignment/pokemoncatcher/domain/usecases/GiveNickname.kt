package com.assignment.pokemoncatcher.domain.usecases

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import javax.inject.Inject

class GiveNickname @Inject constructor(
    private val myPokemonsRepo: MyPokemonsRepository
) {
    suspend fun execute(
        id: Int,
        nickname: String
    ): Either<AppError, Unit> {
        val myPokemon =
            myPokemonsRepo.get(id)
                ?: return Either.left(
                    AppError(
                        "Cannot give nickname. This pokemon is not yours"
                    )
                )

        val updMyPokemon =
            myPokemon.copy(
                nickname = nickname
            )
        return myPokemonsRepo.updateMyPokemon(
            updMyPokemon
        )
    }
}