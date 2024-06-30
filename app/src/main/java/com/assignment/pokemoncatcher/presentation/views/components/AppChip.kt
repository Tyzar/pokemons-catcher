package com.assignment.pokemoncatcher.presentation.views.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AppChip(
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    text: String
) {
    Box(
        modifier = Modifier
            .background(
                backgroundColor
            )
            .clip(shape = MaterialTheme.shapes.extraSmall),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(4.dp),
            text = text.capitalize(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium.copy(
                color = contentColor
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun ChipPreview() {
    AppChip(text = "Poison")
}