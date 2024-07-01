package com.assignment.pokemoncatcher.presentation.uistate.release_pokemon

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either

data class ReleasePokemonState(
    val isReleasing: Boolean = true,
    val releaseResult: Either<AppError, Boolean>? = null
)