package com.assignment.pokemoncatcher.presentation.uistate.catch_pokemon

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either

data class CatchPokemonState(
    val isCatching: Boolean = false,
    val catchResult: Either<AppError, Boolean>? = null,
    val isGivingName: Boolean = false,
    val giveNicknameResult: Either<AppError, Unit>? = null
)