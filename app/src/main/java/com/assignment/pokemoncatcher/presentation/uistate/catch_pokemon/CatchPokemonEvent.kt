package com.assignment.pokemoncatcher.presentation.uistate.catch_pokemon

sealed class CatchPokemonEvent {
    data class CatchPokemon(val id: Int) :
        CatchPokemonEvent()

    data class GiveNickname(
        val id: Int,
        val nickname: String
    ) :
        CatchPokemonEvent()
}