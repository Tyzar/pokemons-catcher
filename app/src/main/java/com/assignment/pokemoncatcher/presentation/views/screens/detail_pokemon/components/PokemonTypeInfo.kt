package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.presentation.views.components.AppChip

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PokemonTypeInfo(
    modifier: Modifier = Modifier,
    types: List<Pokemon.PokemonType>
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        maxItemsInEachRow = 4
    ) {
        types.map {
            AppChip(text = it.typeName)
            Spacer(
                modifier = Modifier.width(
                    8.dp
                )
            )
        }
    }
}