package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PokemonNotFoundInfo(modifier: Modifier) {
    Surface(modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Something wrong. Pokemon data is not found...",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}