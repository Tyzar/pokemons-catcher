package com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.assignment.pokemoncatcher.presentation.uistate.explore_pokemons.ExplorePokemonsEvent
import com.assignment.pokemoncatcher.presentation.uistate.explore_pokemons.ExplorePokemonsVm
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading
import com.assignment.pokemoncatcher.presentation.views.routes.DetailPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.MyPokemonsRoute
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components.ExplorePokemonTopBar
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components.PokemonsGrid

@Composable
fun ExplorePokemonScreen(
    navController: NavHostController,
    explorePokemonsVm: ExplorePokemonsVm
) {
    val exploreState by explorePokemonsVm.state.collectAsStateWithLifecycle()

    val firstLoad = remember {
        derivedStateOf {
            exploreState.pagingOffset == 0
        }
    }

    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(exploreState) {
        if (exploreState.error != null) {
            snackbarHostState.showSnackbar(
                message = exploreState.error?.errMsg
                    ?: "An error occured"
            )
        }
    }

    LaunchedEffect(firstLoad) {
        explorePokemonsVm.notify(
            ExplorePokemonsEvent.Load
        )
    }

    Scaffold(topBar = {
        ExplorePokemonTopBar(
            onMyPokemonsClicked = {
                navController.navigate(
                    MyPokemonsRoute.GROUP_PATH
                )
            })
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { contentPadding ->
        when {
            exploreState.pagingOffset == 0 -> ContentLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    )
            )

            else -> PokemonsGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = contentPadding.calculateTopPadding() + 16.dp,
                        bottom = contentPadding.calculateBottomPadding() + 16.dp
                    ),
                pokemons = exploreState.pokemons,
                onItemClick = {
                    navController.navigate(
                        DetailPokemonRoute().withParam(
                            mapOf(
                                DetailPokemonRoute.ID to it.id
                            )
                        )
                    )
                },
                onLoadNext = {
                    explorePokemonsVm.notify(
                        ExplorePokemonsEvent.Load
                    )
                }
            )
        }
    }
}
