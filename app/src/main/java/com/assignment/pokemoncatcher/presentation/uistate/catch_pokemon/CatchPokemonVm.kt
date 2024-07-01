package com.assignment.pokemoncatcher.presentation.uistate.catch_pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.usecases.CatchPokemon
import com.assignment.pokemoncatcher.domain.usecases.GiveNickname
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchPokemonVm @Inject constructor(
    private val catchPokemon: CatchPokemon,
    private val giveNickname: GiveNickname
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            CatchPokemonState()
        )
    val state = mState.asStateFlow()

    fun notify(event: CatchPokemonEvent) {
        when (event) {
            is CatchPokemonEvent.CatchPokemon -> handleCatchPokemon(
                event
            )

            is CatchPokemonEvent.GiveNickname -> handleGiveNickname(
                event
            )
        }
    }

    private fun handleGiveNickname(event: CatchPokemonEvent.GiveNickname) {
        viewModelScope.launch {
            if (event.nickname.isEmpty()) {
                return@launch
            }

            mState.value =
                mState.value.copy(
                    isGivingName = true
                )
            val result =
                giveNickname.execute(
                    event.id,
                    event.nickname
                )
            mState.value =
                mState.value.copy(
                    isGivingName = false,
                    giveNicknameResult = result
                )
        }
    }

    private fun handleCatchPokemon(event: CatchPokemonEvent.CatchPokemon) {
        viewModelScope.launch {
            mState.value =
                mState.value.copy(
                    isCatching = true
                )

            val result =
                catchPokemon.execute(
                    event.id
                )
            mState.value =
                mState.value.copy(
                    isCatching = false,
                    catchResult = result
                )
        }
    }
}