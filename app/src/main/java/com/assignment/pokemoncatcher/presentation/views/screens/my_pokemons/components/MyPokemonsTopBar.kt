package com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPokemonsTopBar(onBack: () -> Unit) {
    TopAppBar(title = {
        Text(
            text = "My Pokemons",
            style = MaterialTheme.typography.titleLarge
        )
    }, navigationIcon = {
        IconButton(
            onClick = onBack
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "back"
            )
        }
    })
}
