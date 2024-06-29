package com.assignment.pokemoncatcher.datasources.remote.pokemonapi.responses.getpokemondetail

import com.assignment.pokemoncatcher.domain.entities.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class GetPokemonDetailResponse(
    val id: Int?,
    val name: String?,
    val stats: List<PokemonDataStat>?,
    val sprites: Sprite?,
    val types: List<TypeItem>?,
    val moves: List<MoveItem>?
) {
    @Serializable
    data class PokemonDataStat(
        val baseStat: Int?,
        val stat: DataStat?
    ) {
        @Serializable
        data class DataStat(val name: String?)
    }

    @Serializable
    data class TypeItem(
        val slot: Int?,
        val type: TypeItemType?
    ) {
        @Serializable
        class TypeItemType(val name: String?)
    }

    @Serializable
    data class MoveItem(val move: MoveItemMove?) {
        @Serializable
        data class MoveItemMove(val name: String?)
    }

    @Serializable
    data class Sprite(val other: SpriteOther?) {
        @Serializable
        data class SpriteOther(val officialArtwork: OfficialArtwork?) {
            @Serializable
            data class OfficialArtwork(
                val frontShiny: String?
            )
        }
    }
}

fun GetPokemonDetailResponse.toDomain(): Pokemon {
    return Pokemon(
        id = id ?: 0,
        name = name ?: "",
        stats = stats?.map {
            Pokemon.Stat(
                statName = it.stat?.name
                    ?: "",
                statValue = it.baseStat
                    ?: 0
            )
        }
            ?: emptyList(),
        artwork = Pokemon.Artwork(
            url = sprites?.other?.officialArtwork?.frontShiny
        ),
        types = types?.map {
            Pokemon.PokemonType(
                typeName = it.type?.name
                    ?: ""
            )
        } ?: emptyList(),
        moves = moves?.map {
            Pokemon.Move(
                moveName = it.move?.name
                    ?: ""
            )
        } ?: emptyList()
    )
}



