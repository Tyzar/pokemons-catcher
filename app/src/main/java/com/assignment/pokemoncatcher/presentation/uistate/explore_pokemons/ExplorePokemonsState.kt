package com.assignment.pokemoncatcher.presentation.uistate.explore_pokemons

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.domain.entities.Pokemon

data class ExplorePokemonsState(
    //flag loading next paging
    val isLoadingNextPaging: Boolean = false,
    val pagingOffset: Int = 0,

    val pokemons: List<Pokemon> = emptyList(),

    //errors
    val error: AppError? = null
)