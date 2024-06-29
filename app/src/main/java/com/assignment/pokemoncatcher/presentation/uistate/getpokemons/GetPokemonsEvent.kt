package com.assignment.pokemoncatcher.presentation.uistate.getpokemons

sealed class GetPokemonsEvent {
    data object Load :
        GetPokemonsEvent()
}