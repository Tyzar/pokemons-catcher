package com.assignment.pokemoncatcher.presentation.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.assignment.pokemoncatcher.presentation.views.routes.CatchPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.DetailMyPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.DetailPokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.ExplorePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.MyPokemonsRoute
import com.assignment.pokemoncatcher.presentation.views.routes.PokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.ReleasePokemonRoute
import com.assignment.pokemoncatcher.presentation.views.routes.SplashScreenRoute
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.catch_pokemon.CatchPokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.catch_release_pokemon.release_pokemon.ReleasePokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.my_pokemon.DetailMyPokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.detail_pokemon.pokemon.DetailPokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.explore_pokemon.ExplorePokemonScreen
import com.assignment.pokemoncatcher.presentation.views.screens.my_pokemons.MyPokemonsScreen
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
                    navController,
                    hiltViewModel()
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
                    navController = navController,
                    detailPokemonVm = hiltViewModel()
                )
            }

            composable(
                CatchPokemonRoute.PATH,
                arguments = CatchPokemonRoute().getArgs()
            ) {
                val pokemonId =
                    it.arguments?.getInt(
                        CatchPokemonRoute.ID
                    )
                val pokemonName =
                    it.arguments?.getString(
                        CatchPokemonRoute.POKEMON_NAME
                    )
                CatchPokemonScreen(
                    pokemonId = pokemonId
                        ?: 0,
                    pokemonName = pokemonName
                        ?: "",
                    navController = navController,
                    catchPokemonVm = hiltViewModel()
                )
            }

            navigation(
                startDestination = MyPokemonsRoute.PATH,
                route = MyPokemonsRoute.GROUP_PATH
            ) {
                composable(
                    MyPokemonsRoute.PATH
                ) {
                    val parentEntry =
                        remember(it) {
                            navController.getBackStackEntry(
                                MyPokemonsRoute.GROUP_PATH
                            )
                        }
                    MyPokemonsScreen(
                        navController = navController,
                        myPokemonsVm = hiltViewModel(
                            parentEntry
                        )
                    )
                }

                composable(
                    DetailMyPokemonRoute.PATH,
                    arguments = DetailMyPokemonRoute().getArgs()
                ) {
                    val parentEntry =
                        remember(it) {
                            navController.getBackStackEntry(
                                MyPokemonsRoute.GROUP_PATH
                            )
                        }

                    val id: Int =
                        it.arguments?.getString(
                            DetailMyPokemonRoute.ID
                        )?.toInt() ?: 0

                    DetailMyPokemonScreen(
                        id = id,
                        navController = navController,
                        detailMyPokemonVm = hiltViewModel(),
                        myPokemonsVm = hiltViewModel(
                            parentEntry
                        )
                    )
                }

                composable(
                    ReleasePokemonRoute.PATH,
                    arguments = ReleasePokemonRoute().getArgs()
                ) {
                    val parentEntry =
                        remember(it) {
                            navController.getBackStackEntry(
                                MyPokemonsRoute.GROUP_PATH
                            )
                        }

                    val id: Int =
                        it.arguments?.getString(
                            ReleasePokemonRoute.ID
                        )?.toInt() ?: 0
                    val name =
                        it.arguments?.getString(
                            ReleasePokemonRoute.NAME
                        ) ?: ""

                    ReleasePokemonScreen(
                        id = id,
                        name = name,
                        navController = navController,
                        releasePokemonVm = hiltViewModel(),
                        myPokemonsVm = hiltViewModel(
                            parentEntry
                        )
                    )
                }
            }
        }
    }
}