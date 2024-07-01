package com.assignment.pokemoncatcher.presentation.uistate.detail_my_pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import com.assignment.pokemoncatcher.domain.usecases.RenamePokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMyPokemonVm @Inject constructor(
    private val myPokemonsRepository: MyPokemonsRepository,
    private val renamePokemon: RenamePokemon
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            DetailMyPokemonState()
        )
    val state = mState.asStateFlow()

    fun notify(event: DetailMyPokemonEvent) {
        when (event) {
            is DetailMyPokemonEvent.Load -> handleLoad(
                event
            )

            is DetailMyPokemonEvent.Rename -> handleRename(
                event
            )
        }
    }

    private fun handleLoad(event: DetailMyPokemonEvent.Load) {
        viewModelScope.launch {
            if (mState.value.isLoading) {
                return@launch
            }

            mState.value =
                mState.value.copy(
                    isLoading = true
                )
            val result =
                myPokemonsRepository.get(
                    event.id
                )
            if (result == null) {
                mState.value =
                    mState.value.copy(
                        isFirstLoad = false,
                        isLoading = false,
                        error = AppError(
                            "Pokemon data is not found"
                        )
                    )
                return@launch
            }

            mState.value =
                mState.value.copy(
                    isFirstLoad = false,
                    isLoading = false,
                    error = null,
                    myPokemon = result
                )
        }
    }

    private fun handleRename(event: DetailMyPokemonEvent.Rename) {
        viewModelScope.launch {
            if (mState.value.myPokemon == null) {
                return@launch
            }

            val renameResult =
                renamePokemon.execute(
                    mState.value.myPokemon!!
                )
            mState.value =
                mState.value.copy(
                    error = null,
                    renameResult = renameResult
                )
        }
    }
}