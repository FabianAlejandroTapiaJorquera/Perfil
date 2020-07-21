package com.example.perfil.repositorio.nube

import com.example.perfil.Resultado
import com.example.perfil.Universidad

interface IUniversidadesDB {

    suspend fun obtenerUniversidades(): Resultado<List<Universidad>>
}