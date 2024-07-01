package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.domain.entities.Pokemon

@Composable
fun MoveSetInfo(
    modifier: Modifier = Modifier,
    moves: List<Pokemon.Move>,
    maxMoveDisplayed: Int = 5
) {
    Column {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            text = "Moves",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )

        val strBuilder = StringBuilder()
        for (i in moves.indices) {
            if (i >= 5) {
                break
            }

            if (i > 0) {
                strBuilder.append(" • ")
            }
            strBuilder.append(
                moves[i].moveName.replace(
                    "-",
                    " "
                ).capitalize()
            )
        }
        Text(
            text = strBuilder.toString(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Light
            )
        )
    }
}