package com.assignment.pokemoncatcher.presentation.views.screens.catch_pokemon.components

import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading

@Composable
fun CatchProgress(
    modifier: Modifier = Modifier,
    pokemonName: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ContentLoading(
            modifier = Modifier.size(
                150.dp
            )
        )

        val infiniteTransition =
            rememberInfiniteTransition(
                label = "dotTick"
            )
        val dotNumbers by infiniteTransition.animateValue(
            initialValue = 0,
            targetValue = 4,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    4000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ), label = "dotTween"
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Catching $pokemonName ${
                ". ".repeat(
                    dotNumbers
                )
            }",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}