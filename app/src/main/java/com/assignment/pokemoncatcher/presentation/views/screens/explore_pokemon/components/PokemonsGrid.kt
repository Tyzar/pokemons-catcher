package com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.assignment.pokemoncatcher.domain.entities.Pokemon

@Composable
fun PokemonsGrid(
    modifier: Modifier = Modifier,
    pokemons: List<Pokemon>,
    onItemClick: (pokemon: Pokemon) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(
            horizontal = 16.dp
        ),
        columns = GridCells.Fixed(
            3
        ),
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            8.dp
        )
    ) {
        items(
            count = pokemons.size,
            key = { pokemons[it].id }) {
            PokemonGridItem(
                pokemon = pokemons[it],
                onItemClick = {
                    onItemClick(pokemons[it])
                })
        }
    }
}

@Composable
fun PokemonGridItem(
    pokemon: Pokemon,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier.clickable {
            onItemClick()
        },
        shape = MaterialTheme.shapes.small
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            model = ImageRequest.Builder(
                LocalContext.current
            )
                .data(pokemon.artwork?.url)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "image-pokemon-${pokemon.id}"
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(
                    8.dp
                ),
                text = pokemon.name,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}