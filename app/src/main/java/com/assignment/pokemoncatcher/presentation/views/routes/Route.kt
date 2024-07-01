package com.assignment.pokemoncatcher.presentation.views.routes

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Route {
    fun getArgs(): List<NamedNavArgument>

    fun withParam(param: Map<String, Any>? = null): String
}

class SplashScreenRoute : Route {
    companion object {
        const val PATH = "splashscreen"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return emptyList()
    }

    override fun withParam(param: Map<String, Any>?): String {
        return PATH
    }
}

class PokemonRoute : Route {
    companion object {
        const val PATH = "pokemon"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return emptyList()
    }

    override fun withParam(param: Map<String, Any>?): String {
        return PATH
    }
}

class ExplorePokemonRoute : Route {
    companion object {
        const val PATH =
            "explore-pokemon"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return emptyList()
    }

    override fun withParam(param: Map<String, Any>?): String {
        return PATH
    }
}

class DetailPokemonRoute : Route {
    companion object {
        const val ID = "id"
        const val PATH =
            "pokemon-detail/{$ID}"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return listOf(navArgument(
            ID
        ) {
            type = NavType.IntType
        })
    }

    override fun withParam(param: Map<String, Any>?): String {
        return "pokemon-detail/${
            param?.get(
                ID
            )
        }"
    }
}

class MyPokemonsRoute : Route {
    companion object {
        const val PATH = "my-pokemons"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return emptyList()
    }

    override fun withParam(param: Map<String, Any>?): String {
        return PATH
    }

}

class CatchPokemonRoute : Route {
    companion object ParamKey {
        const val ID = "id"
        const val POKEMON_NAME =
            "pokemon-name"
        const val PATH =
            "catch-pokemon/{$ID}/{$POKEMON_NAME}"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return listOf(navArgument(ID) {
            type = NavType.IntType
        }, navArgument(POKEMON_NAME) {
            type = NavType.StringType
        })
    }

    override fun withParam(param: Map<String, Any>?): String {
        return "catch-pokemon/${
            param?.get(
                ID
            )
        }/${param?.get(POKEMON_NAME)}"
    }

}

class ReleasePokemonRoute : Route {
    companion object ParamKey {
        const val ID = "id"
        const val PATH =
            "release-pokemon/{$ID}"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return listOf(navArgument(ID) {
            type = NavType.IntType
        })
    }

    override fun withParam(param: Map<String, Any>?): String {
        return "release-pokemon/${
            param?.get(
                ID
            )
        }"
    }

}

class GiveNicknameRoute : Route {
    companion object {
        const val PATH = "give-nickname"
    }

    override fun getArgs(): List<NamedNavArgument> {
        return emptyList()
    }

    override fun withParam(param: Map<String, Any>?): String {
        return PATH
    }
}

