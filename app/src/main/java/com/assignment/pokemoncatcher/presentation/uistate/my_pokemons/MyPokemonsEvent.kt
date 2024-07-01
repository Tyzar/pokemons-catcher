package com.assignment.pokemoncatcher.presentation.uistate.my_pokemons

sealed class MyPokemonsEvent {
    data object Load : MyPokemonsEvent()
}
