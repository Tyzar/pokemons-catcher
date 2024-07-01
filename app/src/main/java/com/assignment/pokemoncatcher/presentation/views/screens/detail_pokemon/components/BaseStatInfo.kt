package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.domain.entities.Pokemon

@Composable
fun BaseStatInfo(
    modifier: Modifier = Modifier,
    stats: List<Pokemon.Stat>,
    maxDisplayStat: Int = 6
) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = false
    ) {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = "Base Stat",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        items(
            count = if (stats.size > maxDisplayStat) maxDisplayStat else stats.size,
            key = { stats[it].statName }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stats[it].statName.capitalize(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    text = "${stats[it].statValue}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}