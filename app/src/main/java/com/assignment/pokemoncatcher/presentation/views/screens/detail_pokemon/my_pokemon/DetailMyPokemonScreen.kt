package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.my_pokemon

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
import com.assignment.pokemoncatcher.core.utils.Either
import com.assignment.pokemoncatcher.domain.entities.MyPokemon
import com.assignment.pokemoncatcher.presentation.uistate.detail_my_pokemon.DetailMyPokemonEvent
import com.assignment.pokemoncatcher.presentation.uistate.detail_my_pokemon.DetailMyPokemonVm
import com.assignment.pokemoncatcher.presentation.uistate.my_pokemons.MyPokemonsEvent
import com.assignment.pokemoncatcher.presentation.uistate.my_pokemons.MyPokemonsVm
import com.assignment.pokemoncatcher.presentation.views.components.ActionButtonWithIcon
import com.assignment.pokemoncatcher.presentation.views.components.ContentLoading
import com.assignment.pokemoncatcher.presentation.views.routes.ReleasePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.DetailPokemonTopBar
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.MoveSetInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.NicknameSection
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.PokemonNotFoundInfo
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components.PokemonTypeInfo
import kotlin.math.min

@Composable
fun DetailMyPokemonScreen(
    id: Int,
    navController: NavController,
    detailMyPokemonVm: DetailMyPokemonVm,
    myPokemonsVm: MyPokemonsVm
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val detailState by detailMyPokemonVm.state.collectAsStateWithLifecycle()

    LaunchedEffect(detailState.error) {
        if (detailState.error != null) {
            snackbarHostState.showSnackbar(
                detailState.error?.errMsg
                    ?: "An error occurred"
            )
        }
    }

    LaunchedEffect(detailState.isFirstLoad) {
        if (detailState.isFirstLoad) {
            detailMyPokemonVm.notify(
                DetailMyPokemonEvent.Load(
                    id
                )
            )
        }
    }

    LaunchedEffect(detailState.renameResult) {
        when (detailState.renameResult) {
            is Either.left -> {
                snackbarHostState.showSnackbar(
                    detailState.error?.errMsg
                        ?: "An error occurred"
                )
            }

            is Either.right -> {
                //reload detail
                detailMyPokemonVm.notify(
                    DetailMyPokemonEvent.Load(
                        id
                    )
                )

                //reload my pokemon list
                myPokemonsVm.notify(
                    MyPokemonsEvent.Load
                )
            }

            null -> {}
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
    }, bottomBar = {
        if (detailState.myPokemon != null) {
            ActionButtonWithIcon(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                label = "Release It",
                onClick = {
                    navController.navigate(
                        ReleasePokemonRoute().withParam(
                            mapOf(
                                ReleasePokemonRoute.ID to id,
                                ReleasePokemonRoute.NAME to detailState.myPokemon!!
                                    .nickname
                            )
                        )
                    )
                }
            )
        }
    }) { contentPadding ->
        when (detailState.isLoading || detailState.isFirstLoad) {
            true -> ContentLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        contentPadding
                    )
            )

            else -> when (detailState.myPokemon) {
                null -> PokemonNotFoundInfo(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            contentPadding
                        )
                )

                else -> DetailContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = contentPadding.calculateTopPadding() + 16.dp,
                            bottom = contentPadding.calculateBottomPadding() + 16.dp,
                            start = 16.dp,
                            end = 16.dp
                        ),
                    myPokemon = detailState.myPokemon!!,
                    onRenameClicked = {
                        detailMyPokemonVm.notify(
                            DetailMyPokemonEvent.Rename(
                                id
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier = Modifier,
    myPokemon: MyPokemon,
    onRenameClicked: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
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
                model = myPokemon.pokemon.artwork?.url,
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )
        }
        item {
            NicknameSection(
                modifier = Modifier.padding(
                    vertical = 16.dp
                ),
                currentNickname = myPokemon.nickname,
                onRename = onRenameClicked
            )
        }
        item {
            Text(
                text = myPokemon.pokemon.name.capitalize(),
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
                types = myPokemon.pokemon.types
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
                myPokemon.pokemon.stats.size,
                6
            ),
            key = { myPokemon.pokemon.stats[it].statName }) {
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
                    text = myPokemon.pokemon.stats[it].statName.capitalize(),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Normal
                    )
                )
                Text(
                    text = "${myPokemon.pokemon.stats[it].statValue}",
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
                moves = myPokemon.pokemon.moves
            )
        }
    }
}