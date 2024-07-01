package com.assignment.pokemoncatcher.presentation.uistate.my_pokemons

import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.domain.entities.MyPokemon

data class MyPokemonsState(
    val isLoading: Boolean = false,
    val myPokemons: List<MyPokemon> = emptyList(),
    val error: AppError? = null
)