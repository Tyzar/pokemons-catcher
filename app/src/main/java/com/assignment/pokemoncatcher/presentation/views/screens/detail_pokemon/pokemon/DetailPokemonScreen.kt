package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.assignment.pokemoncatcher.core.utils.DebugLog
import com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon.DetailPokemonEvent
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading
import com.assignment.pokemoncatcher.presentation.views.routes.CatchPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.DetailPokemonTopBar
import com.assignment.pokemoncatcher.presentation.views.components.ActionButtonWithIcon
import com.assignment.pokemoncatcher.presentation.uistate.detail_pokemon.DetailPokemonVm
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.MoveSetInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.PokemonNotFoundInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.PokemonTypeInfo
import kotlin.math.min

@Composable
fun DetailPokemonScreen(
    id: Int,
    navController: NavController,
    detailPokemonVm: DetailPokemonVm
) {
    val detailPokeState by detailPokemonVm.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(detailPokeState.error) {
        if (detailPokeState.error != null) {
            snackbarHostState.showSnackbar(
                detailPokeState.error?.errMsg
                    ?: "An error occured"
            )
        }
    }

    LaunchedEffect(detailPokeState.isFirstLoad) {
        if (detailPokeState.isFirstLoad) {
            detailPokemonVm.notify(
                DetailPokemonEvent.GetDetail(
                    id
                )
            )
        }
    }

    Scaffold(topBar = {
        DetailPokemonTopBar {
            navController.popBackStack()
        }
    }, snackbarHost = {
        SnackbarHost(
            hostState = snackbarHostState
        )
    },
        bottomBar = {
            if (detailPokeState.pokemon != null && !detailPokeState.hasCaught) {
                ActionButtonWithIcon(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 4.dp,
                            bottom = 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    label = "Catch It"
                ) {
                    navController.navigate(
                        CatchPokemonRoute().withParam(
                            mapOf(
                                CatchPokemonRoute.ID to id,
                                CatchPokemonRoute.POKEMON_NAME to detailPokeState.pokemon!!.name
                            )
                        )
                    )
                }
            }
        }) { contentPadding ->
        DebugLog.log(msg = "Content padding : ${contentPadding.calculateBottomPadding()}")
        when {
            detailPokeState.isLoading -> ContentLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    )
            )

            else -> {
                val pokemon =
                    detailPokeState.pokemon
                when (pokemon) {
                    null -> PokemonNotFoundInfo(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                contentPadding
                            )
                    )

                    else -> LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                top = contentPadding.calculateTopPadding() + 16.dp,
                                bottom = contentPadding.calculateBottomPadding() + 8.dp,
                                start = 16.dp,
                                end = 16.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    20.dp
                                )
                            )
                        }
                        item {
                            AsyncImage(
                                modifier = Modifier.size(
                                    200.dp
                                ),
                                model = pokemon.artwork?.url,
                                contentScale = ContentScale.FillWidth,
                                contentDescription = ""
                            )
                        }
                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    16.dp
                                )
                            )
                        }
                        item {
                            Text(
                                text = pokemon.name.capitalize(),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    16.dp
                                )
                            )
                        }
                        item {
                            PokemonTypeInfo(
                                types = pokemon.types
                            )
                        }
                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    16.dp
                                )
                            )
                        }
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 16.dp
                                    ),
                                text = "Base Stat",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        items(
                            count = min(
                                pokemon.stats.size,
                                6
                            ),
                            key = { pokemon.stats[it].statName }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        vertical = 8.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = pokemon.stats[it].statName.capitalize(),
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                                Text(
                                    text = "${pokemon.stats[it].statValue}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                        item {
                            Spacer(
                                modifier = Modifier.height(
                                    16.dp
                                )
                            )
                        }
                        item {
                            MoveSetInfo(
                                moves = pokemon.moves
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
//@Composable
//fun DetailPokemonScreenPreview() {
//    DetailPokemonScreen(
//        id = 1,
//        navController = rememberNavController()
//    )
//}
