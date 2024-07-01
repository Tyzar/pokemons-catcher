package com.assignment.pokemoncatcher.presentation.uistate.explore_pokemons

sealed class ExplorePokemonsEvent {
    data object Load :
        ExplorePokemonsEvent()
}