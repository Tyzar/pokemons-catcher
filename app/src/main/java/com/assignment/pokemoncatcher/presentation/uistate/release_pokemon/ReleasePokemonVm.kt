package com.assignment.pokemoncatcher.presentation.uistate.release_pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.domain.usecases.ReleasePokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleasePokemonVm @Inject constructor(
    private val releasePokemon: ReleasePokemon
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            ReleasePokemonState()
        )
    val state = mState.asStateFlow()

    fun notify(event: ReleasePokemonEvent) {
        when (event) {
            is ReleasePokemonEvent.Release -> handleRelease(
                event
            )
        }
    }

    private fun handleRelease(event: ReleasePokemonEvent.Release) {
        viewModelScope.launch {
            mState.value =
                mState.value.copy(
                    isReleasing = true
                )
            val releaseResult =
                releasePokemon.execute(
                    event.id
                )
            mState.value =
                mState.value.copy(
                    releaseResult = releaseResult,
                    isReleasing = false
                )
        }
    }
}