package com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.catch_pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.assignment.pokemoncatcher.core.errors.AppError
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.presentation.uistate.catch_pokemon.CatchPokemonEvent
import com.assignment.pokemoncatcher.presentation.uistate.catch_pokemon.CatchPokemonVm
import com.assignment.pokemoncatcher.presentation.views.routes.ExplorePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.components.ActionError
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.components.ActionProgress
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.catch_pokemon.components.CatchSuccess

@Composable
fun CatchPokemonScreen(
    pokemonId: Int,
    pokemonName: String,
    catchPokemonVm: CatchPokemonVm,
    navController: NavController
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val catchState by catchPokemonVm.state.collectAsStateWithLifecycle()

    LaunchedEffect(catchState.isCatching) {
        if (catchState.catchResult == null && !catchState.isCatching) {
            catchPokemonVm.notify(
                CatchPokemonEvent.CatchPokemon(
                    pokemonId
                )
            )
        }
    }

    LaunchedEffect(catchState.giveNicknameResult) {
        when (catchState.giveNicknameResult) {
            is Either.left -> {
                snackbarHostState.showSnackbar(
                    message = (catchState.giveNicknameResult as Either.left<AppError, Unit>).value.errMsg
                )
            }

            is Either.right -> {
                navController.popBackStack(
                    ExplorePokemonRoute.PATH,
                    inclusive = false
                )
            }

            null -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { contentPadding ->
        when (catchState.isCatching) {
            true -> ActionProgress(
                modifier = Modifier.fillMaxSize(),
                pokemonName = pokemonName,
                actionText = "Catching"
            )

            false -> when (catchState.catchResult) {
                is Either.left -> ActionError(
                    modifier = Modifier.fillMaxSize(),
                    message = (catchState.catchResult as Either.left<AppError, Boolean>).value.errMsg,
                    onTryAgain = {
                        catchPokemonVm.notify(
                            CatchPokemonEvent.CatchPokemon(
                                pokemonId
                            )
                        )
                    }
                )

                is Either.right -> when ((catchState.catchResult as Either.right<AppError, Boolean>).value) {
                    true -> CatchSuccess(
                        modifier = Modifier.fillMaxSize(),
                        pokemonName = pokemonName,
                        onNicknameGiven = {
                            catchPokemonVm.notify(
                                CatchPokemonEvent.GiveNickname(
                                    pokemonId,
                                    it
                                )
                            )
                        }
                    )

                    false -> ActionError(
                        modifier = Modifier.fillMaxSize(),
                        message = "You failed to catch $pokemonName",
                        onTryAgain = {
                            catchPokemonVm.notify(
                                CatchPokemonEvent.CatchPokemon(
                                    pokemonId
                                )
                            )
                        })
                }

                null -> ActionProgress(
                    modifier = Modifier.fillMaxSize(),
                    pokemonName = pokemonName,
                    actionText = "Catching"
                )
            }
        }
    }
}