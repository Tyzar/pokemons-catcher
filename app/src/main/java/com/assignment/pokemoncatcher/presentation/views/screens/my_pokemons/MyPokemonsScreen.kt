package com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.assignment.pokemoncatcher.presentation.uistate.my_pokemons.MyPokemonsVm
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading
import com.assignment.pokemoncatcher.presentation.views.routes.DetailMyPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons.components.MyPokemonsList
import com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons.components.MyPokemonsTopBar

@Composable
fun MyPokemonsScreen(
    navController: NavController,
    myPokemonsVm: MyPokemonsVm
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val myPokeState by
    myPokemonsVm.state.collectAsStateWithLifecycle()

    LaunchedEffect(myPokeState.error) {
        if (myPokeState.error != null) {
            snackbarHostState.showSnackbar(
                myPokeState.error?.errMsg
                    ?: "An error occured"
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MyPokemonsTopBar(onBack = {
                navController.popBackStack()
            })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { contentPadding ->
        when (myPokeState.isLoading) {
            true -> ContentLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    )
                    .padding(horizontal = 16.dp)
            )

            false -> MyPokemonsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    )
                    .padding(horizontal = 16.dp),
                myPokemons = myPokeState.myPokemons
            ) {
                navController.navigate(
                    DetailMyPokemonRoute().withParam(
                        mapOf(
                            DetailMyPokemonRoute.ID to it.pokemon.id
                        )
                    )
                )
            }
        }
    }
}