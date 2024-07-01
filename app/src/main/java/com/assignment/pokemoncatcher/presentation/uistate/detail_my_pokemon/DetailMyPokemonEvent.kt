package com.assignment.pokemoncatcher.presentation.uistate.detail_my_pokemon

sealed class DetailMyPokemonEvent {
    data class Load(val id: Int) :
        DetailMyPokemonEvent()

    data class Rename(val id: Int) :
        DetailMyPokemonEvent()
}