package com.example.perfil.repositorio.local

import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.Universidad

interface UniversidadesUseCase {

    suspend fun traerUniversidadesNube(): Resultado<List<Universidad>>

}