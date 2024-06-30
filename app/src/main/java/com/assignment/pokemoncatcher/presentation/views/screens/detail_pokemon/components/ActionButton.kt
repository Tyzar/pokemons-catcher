package com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.R

@Composable
fun ActionButton(
    modifier: Modifier,
    label: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
    ) {
        Image(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.poke_ball_logo),
            contentDescription = ""
        )
        Spacer(
            modifier = Modifier.width(
                8.dp
            )
        )
        Text(text = label.uppercase())
    }
}

@Preview
@Composable
fun CatchButtonPreview() {
    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        label = "Catch It!!"
    ) {

    }
}