package com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon

sealed class DetailPokemonEvent {
    data class GetDetail(val id: Int) :
        DetailPokemonEvent()
}