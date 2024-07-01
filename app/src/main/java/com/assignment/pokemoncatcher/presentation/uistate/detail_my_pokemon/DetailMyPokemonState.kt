package com.assignment.pokemoncatcher.presentation.uistate.detail_my_pokemon

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon

data class DetailMyPokemonState(
    val isFirstLoad: Boolean = true,
    val isLoading: Boolean = false,
    val myPokemon: MyPokemon? = null,
    val error: AppError? = null,
    val renameResult: Either<AppError, Unit>? = null
)