package com.assignment.pokemoncatcher.presentation.uistate.release_pokemon

sealed class ReleasePokemonEvent {
    data class Release(val id: Int) :
        ReleasePokemonEvent()
}