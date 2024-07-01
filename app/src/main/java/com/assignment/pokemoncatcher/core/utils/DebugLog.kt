package com.assignment.pokemoncatcher.core.utils

import android.util.Log
import com.assignment.pokemoncatcher.BuildConfig

abstract class DebugLog {
    enum class Verbose {
        ERROR,
        INFO
    }

    companion object {
        fun log(
            v: Verbose = Verbose.INFO,
            tag: String = "PokeApp",
            msg: String
        ) {
            if (!BuildConfig.DEBUG) {
                return
            }

            when (v) {
                Verbose.ERROR -> Log.e(
                    tag,
                    msg
                )

                Verbose.INFO -> Log.d(
                    tag,
                    msg
                )
            }
        }
    }
}