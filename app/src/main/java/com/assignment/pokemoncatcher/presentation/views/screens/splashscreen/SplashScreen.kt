package com.assignment.pokemoncatcher.presentation.views.screens.splashscreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.assignment.pokemoncatcher.R
import com.assignment.pokemoncatcher.presentation.views.routes.PokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.SplashScreenRoute
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LaunchedEffect(Unit) {
                delay(1500)
                navController.navigate(
                    PokemonRoute().withParam()
                ) {
                    popUpTo(
                        SplashScreenRoute.PATH
                    ) {
                        inclusive = true
                    }
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(
                        id = R.drawable.poke_ball_logo
                    ),
                    contentDescription = "logo",
                    modifier = Modifier.size(
                        100.dp
                    )
                )
                Spacer(
                    modifier = Modifier.height(
                        16.dp
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.app_name
                    ),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}