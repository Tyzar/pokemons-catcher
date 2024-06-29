package com.assignment.pokemoncatcher.core.utils

sealed class Either<out L, out R> {
    data class left<out L, out R>(val value: L) :
        Either<L, R>()

    data class right<out L, out R>(val value: R) :
        Either<L, R>()
}