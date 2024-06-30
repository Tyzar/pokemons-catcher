package com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.presentation.views.routes.DetailPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components.ExplorePokemonTopBar
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components.PokemonsGrid

@Composable
fun ExplorePokemonScreen(navController: NavHostController) {
    Scaffold(topBar = {
        ExplorePokemonTopBar()
    }) { contentPadding ->
        val pokemons =
            mutableListOf<Pokemon>()
        for (i in 0..10) {
            pokemons.add(
                Pokemon(
                    id = i + 1,
                    name = "Pokemon ${i + 1}",
                    artwork = Pokemon.Artwork(
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/${i + 1}.png"
                    )
                )
            )
        }
        PokemonsGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding() + 16.dp,
                    bottom = contentPadding.calculateBottomPadding() + 16.dp
                ),
            pokemons = pokemons,
            onItemClick = {
                navController.navigate(
                    DetailPokemonRoute().withParam(
                        mapOf(
                            DetailPokemonRoute.ID to it.id
                        )
                    )
                )
            }
        )
    }
}
