package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NicknameSection(
    modifier: Modifier = Modifier,
    currentNickname: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = currentNickname,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier.width(
                8.dp
            )
        )
        IconButton(
            modifier = Modifier.size(
                18.dp
            ), onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = "rename"
            )
        }
    }
}