package com.assignment.pokemoncatcher.presentation.uistate.explore_pokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.pokemoncatcher.core.utils.DebugLog
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.FetchFilter
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.domain.repositories.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExplorePokemonsVm @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val mState =
        MutableStateFlow(
            ExplorePokemonsState()
        )
    val state = mState.asStateFlow()

    fun notify(event: ExplorePokemonsEvent) {
        when (event) {
            ExplorePokemonsEvent.Load -> handleLoad()
        }
    }

    private fun handleLoad() {
        viewModelScope.launch {
            val isFirstLoad =
                mState.value.pagingOffset == 0
            if (!isFirstLoad && mState.value.isLoadingNextPaging) {
                DebugLog.log(msg = "Reject load because still loading paging...")
                return@launch
            }

            DebugLog.log(msg = "Handle load pokemons data...")
            mState.value =
                mState.value.copy(
                    isLoadingNextPaging = !isFirstLoad
                )
            val result =
                pokemonRepository.getPokemonList(
                    filter = FetchFilter(
                        resultOffset = mState.value.pagingOffset
                    )
                )

            when (result) {
                is Either.left -> mState.value =
                    mState.value.copy(
                        isLoadingNextPaging = false,
                        error = result.value
                    )

                is Either.right -> {
                    val newPokemons =
                        result.value
                    val newOffset =
                        mState.value.pagingOffset + newPokemons.size

                    DebugLog.log(msg = "New pokemon count: ${newPokemons.size} | offset: $newOffset")

                    mState.value =
                        mState.value.copy(
                            pokemons = addPokemons(
                                newPokemons
                            ),
                            pagingOffset = newOffset,
                            isLoadingNextPaging = false,
                            error = null
                        )
                }
            }
        }
    }

    private fun addPokemons(newPokemons: List<Pokemon>): List<Pokemon> {
        if (newPokemons.isEmpty()) {
            return mState.value.pokemons
        }

        val currPokemons =
            mState.value.pokemons.toMutableList()
        currPokemons.addAll(newPokemons)
        return currPokemons
    }
}