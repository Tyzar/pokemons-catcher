package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GiveNicknameButton(
    modifier: Modifier = Modifier,
    onClickGiveNickname: () -> Unit
) {
    Text(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clip(shape = MaterialTheme.shapes.extraSmall)
            .padding(
                vertical = 8.dp,
                horizontal = 4.dp
            )
            .clickable {
                onClickGiveNickname()
            },
        textAlign = TextAlign.Center,
        text = "Give a nickname",
        style = MaterialTheme.typography.labelMedium
    )
}