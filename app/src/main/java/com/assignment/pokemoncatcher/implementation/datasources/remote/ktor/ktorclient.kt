package com.assignment.pokemoncatcher.implementation.datasources.remote.ktor

import android.util.Log
import com.assignment.pokemoncatcher.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

const val REQUEST_TIMEOUT = 30000

fun createKtorHttpClient(baseUrl: String): HttpClient {
    return HttpClient(Android) {
        expectSuccess = true

        defaultRequest {
            url(baseUrl)
        }

        engine {
            connectTimeout =
                REQUEST_TIMEOUT
            socketTimeout =
                REQUEST_TIMEOUT
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    if (BuildConfig.DEBUG) {
                        Log.d(
                            "ktor",
                            message
                        )
                    }
                }

            }

            level = LogLevel.ALL
        }
    }
}