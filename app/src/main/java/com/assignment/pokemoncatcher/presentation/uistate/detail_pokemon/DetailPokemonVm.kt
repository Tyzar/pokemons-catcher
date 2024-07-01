package com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon.DetailPokemonEvent
import com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon.DetailPokemonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonVm @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val myPokemonsRepository: MyPokemonsRepository
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            DetailPokemonState()
        )
    val state = mState.asStateFlow()

    fun notify(event: DetailPokemonEvent) {
        when (event) {
            is DetailPokemonEvent.GetDetail -> handleGetDetail(
                event
            )
        }
    }

    private fun handleGetDetail(event: DetailPokemonEvent.GetDetail) {
        viewModelScope.launch {
            if (mState.value.isLoading) {
                return@launch
            }

            mState.value =
                mState.value.copy(
                    isLoading = true
                )

            val pokemon: Pokemon?
            val myPokemon =
                myPokemonsRepository.get(
                    event.id
                )
            pokemon = myPokemon?.pokemon
                ?: pokemonRepository.getPokemonDetail(
                    event.id
                )

            when (pokemon) {
                null -> mState.value =
                    mState.value.copy(
                        isLoading = false
                    )

                else -> mState.value =
                    mState.value.copy(
                        pokemon = pokemon,
                        hasCaught = myPokemon != null,
                        isLoading = false,
                        error = null
                    )
            }
        }
    }
}