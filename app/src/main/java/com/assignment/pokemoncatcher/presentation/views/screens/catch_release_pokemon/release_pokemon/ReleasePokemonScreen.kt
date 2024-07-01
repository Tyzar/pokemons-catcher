package com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.release_pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.assignment.pokemoncatcher.presentation.uistate.my_pokemons.MyPokemonsEvent
import com.assignment.pokemoncatcher.presentation.uistate.my_pokemons.MyPokemonsVm
import com.assignment.pokemoncatcher.presentation.uistate.release_pokemon.ReleasePokemonEvent
import com.assignment.pokemoncatcher.presentation.uistate.release_pokemon.ReleasePokemonVm
import com.assignment.pokemoncatcher.presentation.views.routes.MyPokemonsRoute
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.components.ActionError
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.components.ActionProgress
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.release_pokemon.components.ReleaseSuccess

@Composable
fun ReleasePokemonScreen(
    id: Int,
    name: String,
    navController: NavController,
    releasePokemonVm: ReleasePokemonVm,
    myPokemonsVm: MyPokemonsVm
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val releaseState by releasePokemonVm.state.collectAsStateWithLifecycle()

    LaunchedEffect(releaseState.releaseResult) {
        when (releaseState.releaseResult) {
            is Either.left -> {}

            is Either.right -> {
                //refresh my pokemons
                myPokemonsVm.notify(
                    MyPokemonsEvent.Load
                )
            }

            null -> {
                releasePokemonVm.notify(
                    ReleasePokemonEvent.Release(
                        id
                    )
                )
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { contentPadding ->
        when (releaseState.isReleasing) {
            true -> ActionProgress(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    ),
                pokemonName = name,
                actionText = "Releasing"
            )

            false -> when (releaseState.releaseResult) {
                is Either.left -> ActionError(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            contentPadding
                        ),
                    message = (releaseState.releaseResult as Either.left<AppError, Boolean>).value.errMsg,
                    onTryAgain = {
                        releasePokemonVm.notify(
                            ReleasePokemonEvent.Release(
                                id
                            )
                        )
                    }
                )

                is Either.right -> when ((releaseState.releaseResult as Either.right<AppError, Boolean>).value) {
                    true -> ReleaseSuccess(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                contentPadding
                            ),
                        pokemonName = name,
                        onConfirm = {
                            //navigate to my pokemons
                            navController.popBackStack(
                                MyPokemonsRoute.PATH,
                                inclusive = false
                            )
                        }
                    )

                    false -> ActionError(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                contentPadding
                            ),
                        message = "Failed to release $name",
                        onTryAgain = {
                            releasePokemonVm.notify(
                                ReleasePokemonEvent.Release(
                                    id
                                )
                            )
                        }
                    )
                }

                null -> {}
            }
        }
    }
}