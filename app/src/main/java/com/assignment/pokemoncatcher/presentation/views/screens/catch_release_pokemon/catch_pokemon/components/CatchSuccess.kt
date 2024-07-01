package com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.catch_pokemon.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.presentation.views.components.ActionButton

@Composable
fun CatchSuccess(
    modifier: Modifier = Modifier,
    pokemonName: String,
    onNicknameGiven: (nickname: String) -> Unit
) {
    var nickname by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Catch Success!!",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(
            modifier = Modifier.height(
                24.dp
            )
        )
        Text(
            text = "You've success catch $pokemonName. Now give it a nickname",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier.height(
                16.dp
            )
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.extraSmall,
            value = nickname,
            onValueChange = {
                nickname = it
            }
        )
        Spacer(
            modifier = Modifier.height(
                16.dp
            )
        )
        ActionButton(
            modifier = Modifier.fillMaxWidth(),
            label = "Give Name"
        ) {
            onNicknameGiven(nickname)
        }
    }
}