package com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.assignment.pokemoncatcher.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorePokemonTopBar(
    onMyPokemonsClicked: () -> Unit
) {
    CenterAlignedTopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
    }, actions = {
        IconButton(onClick = onMyPokemonsClicked) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "my pokemons"
            )
        }
    })
}