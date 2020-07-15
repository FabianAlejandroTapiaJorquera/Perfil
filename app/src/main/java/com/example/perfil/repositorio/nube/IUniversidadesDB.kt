package com.example.perfil.repositorio.nube

import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.Universidad

interface IUniversidadesDB {

    suspend fun obtenerUniversidades(): Resultado<List<Universidad>>

}