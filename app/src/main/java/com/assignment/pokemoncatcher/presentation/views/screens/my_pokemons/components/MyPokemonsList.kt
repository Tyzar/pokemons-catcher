package com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.assignment.pokemoncatcher.domain.entities.MyPokemon

@Composable
fun MyPokemonsList(
    modifier: Modifier = Modifier,
    myPokemons: List<MyPokemon>,
    onItemClick: (myPokemon: MyPokemon) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            16.dp
        )
    ) {
        items(
            count = myPokemons.size,
            key = { myPokemons[it].pokemon.id }) {
            MyPokemonListItem(
                myPokemon = myPokemons[it],
                onClick = {
                    onItemClick(
                        myPokemons[it]
                    )
                })
        }
    }
}

@Composable
fun MyPokemonListItem(
    modifier: Modifier = Modifier,
    myPokemon: MyPokemon,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(
                shape = MaterialTheme.shapes.small
            )
            .clickable {
                onClick()
            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(
                        100.dp
                    )
                    .padding(16.dp)
                    .clip(shape = MaterialTheme.shapes.small),
                model = myPokemon.pokemon.artwork?.url,
                clipToBounds = true,
                contentScale = ContentScale.FillWidth,
                contentDescription = "image-${myPokemon.nickname}"
            )
            Spacer(
                modifier = Modifier.width(
                    16.dp
                )
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(
                        1f
                    ),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = myPokemon.nickname,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(
                    modifier = Modifier.height(
                        16.dp
                    )
                )
                Text(
                    text = myPokemon.pokemon.name.capitalize(),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

