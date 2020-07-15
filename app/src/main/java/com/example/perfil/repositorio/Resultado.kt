package com.example.perfil.repositorio

sealed class Resultado<out T> {
    class Loading<out T>: Resultado<T>()
    data class Success<out T>(val dato: T): Resultado<T>()
    data class Error<out T>(val exception: Exception): Resultado<T>()
}