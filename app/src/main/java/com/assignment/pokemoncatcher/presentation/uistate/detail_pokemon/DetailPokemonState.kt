package com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.domain.entities.Pokemon

data class DetailPokemonState(
    val isFirstLoad: Boolean = true,
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null,
    val hasCaught: Boolean = false,
    val error: AppError? = null
)