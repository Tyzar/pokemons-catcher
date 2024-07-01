package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon

import android.content.res.Configuration
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import com.assignment.pokemoncatcher.presentation.views.components.ActionButtonWithIcon
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.BaseStatInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.DetailPokemonTopBar
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.GiveNicknameButton
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.MoveSetInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.NicknameSection
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.PokemonTypeInfo

@Composable
fun DetailMyPokemonScreen(
    id: Int,
    navController: NavController
) {
    val myPokemon = getMyDummyPokemon()

    Scaffold(topBar = {
        DetailPokemonTopBar {
            navController.popBackStack()
        }
    }, bottomBar = {
        ActionButtonWithIcon(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    bottom = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            label = "Release It"
        ) {

        }
    }) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = contentPadding.calculateTopPadding() + 16.dp,
                    bottom = contentPadding.calculateBottomPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(
                    20.dp
                )
            )
            AsyncImage(
                modifier = Modifier.size(
                    200.dp
                ),
                model = myPokemon.pokemon.artwork?.url,
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
            if (myPokemon.nickname.isEmpty())
                GiveNicknameButton(
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    )
                ) {

                }
            else
                NicknameSection(
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    ),
                    currentNickname = myPokemon.nickname
                )
            Text(
                text = myPokemon.pokemon.name.capitalize(),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            PokemonTypeInfo(
                types = myPokemon.pokemon.types
            )
            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            BaseStatInfo(
                modifier = Modifier.fillMaxWidth(),
                stats = myPokemon.pokemon.stats
            )
            Spacer(
                modifier = Modifier.height(
                    16.dp
                )
            )
            MoveSetInfo(
                moves = myPokemon.pokemon.moves
            )
        }
    }
}

private fun getMyDummyPokemon(): MyPokemon {
    return MyPokemon(
        renameCount = 0,
        nickname = "The Great Flying",
        pokemon = Pokemon(
            id = 1,
            name = "Bulbazaur",
            artwork = Pokemon.Artwork(
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/shiny/1.png"
            ),
            moves = listOf(
                Pokemon.Move(
                    moveName = "fire breath"
                ),
                Pokemon.Move(moveName = "tectonic leap")
            ),
            types = listOf(
                Pokemon.PokemonType(
                    "grass"
                ),
                Pokemon.PokemonType("poison")
            ),
            stats = listOf(
                Pokemon.Stat("hp", 45),
                Pokemon.Stat(
                    "attack",
                    49
                ),
                Pokemon.Stat(
                    "defense",
                    49
                ),
                Pokemon.Stat(
                    "special-attack",
                    65
                ),
                Pokemon.Stat(
                    "special-defence",
                    65
                ),
                Pokemon.Stat(
                    "speed",
                    45
                )
            )
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun DetailMyPokemonScreenPreview() {
    DetailMyPokemonScreen(
        id = 1,
        navController = rememberNavController()
    )
}