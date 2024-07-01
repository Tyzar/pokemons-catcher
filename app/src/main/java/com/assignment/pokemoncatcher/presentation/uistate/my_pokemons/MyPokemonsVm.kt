package com.assignment.pokemoncatcher.presentation.uistate.my_pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.repositories.MyPokemonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonsVm @Inject constructor(
    private val myPokeRepo: MyPokemonsRepository
) : ViewModel() {
    private val mState =
        MutableStateFlow(MyPokemonsState())
    val state = mState.asStateFlow()

    init {
        handleLoad()
    }

    fun notify(event: MyPokemonsEvent) {
        when (event) {
            MyPokemonsEvent.Load -> handleLoad()
        }
    }

    private fun handleLoad() {
        viewModelScope.launch {
            if (mState.value.isLoading) {
                return@launch
            }

            mState.value =
                mState.value.copy(
                    isLoading = true
                )

            val result =
                myPokeRepo.getAll()
            when (result) {
                is Either.left -> mState.value =
                    mState.value.copy(
                        isLoading = false,
                        error = result.value
                    )

                is Either.right -> mState.value =
                    mState.value.copy(
                        isLoading = false,
                        error = null,
                        myPokemons = result.value
                    )
            }
        }
    }
}