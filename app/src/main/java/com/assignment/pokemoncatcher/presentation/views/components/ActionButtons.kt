package com.assignment.pokemoncatcher.presentation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.assignment.pokemoncatcher.R

@Composable
fun ActionButtonWithIcon(
    modifier: Modifier,
    enabled: Boolean = true,
    label: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.38f
            ),
            disabledContainerColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.12f
            )
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        val iconImgAlpha =
            if (enabled) 1f else 0.38f
        Image(
            modifier = Modifier
                .size(20.dp)
                .alpha(iconImgAlpha),
            painter = painterResource(id = R.drawable.poke_ball_logo),
            contentDescription = ""
        )
        Spacer(
            modifier = Modifier.width(
                8.dp
            )
        )
        Text(
            text = label.uppercase()
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier,
    enabled: Boolean = true,
    label: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.38f
            ),
            disabledContainerColor = MaterialTheme.colorScheme.surface.copy(
                alpha = 0.12f
            )
        ),
        shape = MaterialTheme.shapes.small,
    ) {
        Spacer(
            modifier = Modifier.width(
                8.dp
            )
        )
        Text(
            text = label.uppercase()
        )
    }
}

@Preview
@Composable
fun CatchButtonPreview() {
    ActionButtonWithIcon(
        modifier = Modifier.fillMaxWidth(),
        label = "Catch It!!",
        enabled = false
    ) {

    }
}