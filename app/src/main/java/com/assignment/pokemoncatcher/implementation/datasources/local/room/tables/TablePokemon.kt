package com.assignment.pokemoncatcher.implementation.datasources.local.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.pokemoncatcher.domain.entities.Pokemon
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.addJsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

@Entity(tableName = "pokemon")
data class TablePokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val artworkUrl: String?,
    val statsJson: String?,
    val typesJson: String?,
    val movesJson: String?
)

fun TablePokemon.toDomain(): Pokemon {
    //decode stats
    val stats = if (statsJson == null) {
        emptyList()
    } else {
        val statsJsonArr =
            Json.decodeFromString<JsonArray>(
                statsJson
            )
        statsJsonArr.filter {
            it.jsonObject["statName"]?.jsonPrimitive != null
        }.map {
            Pokemon.Stat(
                statName = it.jsonObject["statName"]!!.jsonPrimitive.content,
                statValue = it.jsonObject["statValue"]?.jsonPrimitive?.int
                    ?: 0
            )
        }
    }

    val types = if (typesJson == null) {
        emptyList()
    } else {
        val typesJsonArr =
            Json.decodeFromString<JsonArray>(
                typesJson
            )
        typesJsonArr.filter {
            it.jsonObject["typeName"]?.jsonPrimitive != null
        }.map {
            Pokemon.PokemonType(
                typeName = it.jsonObject["typeName"]!!.jsonPrimitive.content
            )
        }
    }

    val moves = if (movesJson == null) {
        emptyList()
    } else {
        val movesJsonArr =
            Json.decodeFromString<JsonArray>(
                movesJson
            )
        movesJsonArr.filter { it.jsonObject["moveName"]?.jsonPrimitive != null }
            .map {
                Pokemon.Move(
                    moveName = it.jsonObject["moveName"]!!.jsonPrimitive.content
                )
            }
    }

    return Pokemon(
        id = id,
        name = name,
        artwork = Pokemon.Artwork(
            url = artworkUrl
        ),
        stats = stats,
        types = types,
        moves = moves
    )
}

fun Pokemon.toTable(): TablePokemon {
    val statsJson =
        if (stats.isEmpty()) {
            null
        } else {
            val jsonArr =
                buildJsonArray {
                    for (stat in stats) {
                        addJsonObject {
                            put(
                                "statName",
                                stat.statName
                            )
                            put(
                                "statValue",
                                stat.statValue
                            )
                        }
                    }
                }
            Json.encodeToString(jsonArr)
        }

    val typesJson =
        if (types.isEmpty()) {
            null
        } else {
            val jsonArr =
                buildJsonArray {
                    for (type in types) {
                        addJsonObject {
                            put(
                                "typeName",
                                type.typeName
                            )
                        }
                    }
                }
            Json.encodeToString(jsonArr)
        }

    val movesJson =
        if (moves.isEmpty()) {
            null
        } else {
            val jsonArr =
                buildJsonArray {
                    for (move in moves) {
                        addJsonObject {
                            put(
                                "moveName",
                                move.moveName
                            )
                        }
                    }
                }
            Json.encodeToString(jsonArr)
        }

    return TablePokemon(
        id = id,
        name = name,
        artworkUrl = artwork?.url,
        statsJson = statsJson,
        typesJson = typesJson,
        movesJson = movesJson
    )
}