package com.assignment.pokemoncatcher.presentation.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.assignment.pokemoncatcher.presentation.views.routes.CatchPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.DetailPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.ExplorePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.GiveNicknameRoute
import com.assignment.pokemoncatcher.presentation.views.routes.MyPokemonsRoute
import com.assignment.pokemoncatcher.presentation.views.routes.PokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.ReleasePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.SplashScreenRoute
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.DetailPokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.ExplorePokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.splashscreen.SplashScreen

@Composable
fun PokemonApp() {
    val navController =
        rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SplashScreenRoute.PATH,
    ) {
        composable(SplashScreenRoute.PATH) {
            SplashScreen(navController = navController)
        }

        navigation(
            startDestination = ExplorePokemonRoute.PATH,
            route = PokemonRoute.PATH
        ) {
            composable(
                ExplorePokemonRoute.PATH
            ) {
                ExplorePokemonScreen(
                    navController
                )
            }

            composable(
                DetailPokemonRoute.PATH,
                arguments = DetailPokemonRoute().getArgs()
            ) {
                val pokemonId =
                    it.arguments?.getInt(
                        DetailPokemonRoute.ID
                    )
                DetailPokemonScreen(
                    id = pokemonId ?: 0,
                    navController = navController
                )
            }

            composable(MyPokemonsRoute.PATH) {

            }

            composable(
                CatchPokemonRoute.PATH,
                arguments = CatchPokemonRoute().getArgs()
            ) {

            }

            composable(
                ReleasePokemonRoute.PATH,
                arguments = ReleasePokemonRoute().getArgs()
            ) {

            }

            composable(GiveNicknameRoute.PATH) {

            }
        }
    }
}