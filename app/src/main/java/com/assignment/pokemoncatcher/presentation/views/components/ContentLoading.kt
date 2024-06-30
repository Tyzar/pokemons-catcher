package com.assignment.pokemoncatcher.presentation.views.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.R

@Composable
fun ContentLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val infiniteTransition =
            rememberInfiniteTransition(
                label = "loadingRotate"
            )
        val rotation by infiniteTransition.animateFloat(
            initialValue = 0F,
            targetValue = 360F,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    1000,
                    easing = LinearEasing
                ),
                repeatMode = RepeatMode.Restart
            ), label = "rotationTween"
        )
        Image(
            modifier = Modifier
                .size(80.dp)
                .rotate(rotation),
            painter = painterResource(id = R.drawable.ic_poke_ball),
            contentDescription = "loading"
        )
    }
}