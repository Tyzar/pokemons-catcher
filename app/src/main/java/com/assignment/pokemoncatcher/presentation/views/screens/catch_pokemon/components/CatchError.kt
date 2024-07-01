package com.assignment.pokemoncatcher.presentation.views.screens.catch_pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.presentation.views.components.ActionButton

@Composable
fun CatchError(
    modifier: Modifier = Modifier,
    message: String,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(
            modifier = Modifier.width(
                16.dp
            )
        )
        ActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            label = "Try Again"
        ) {
            onTryAgain()
        }
    }
}