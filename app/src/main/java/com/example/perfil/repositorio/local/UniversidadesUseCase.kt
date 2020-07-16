package com.example.perfil.repositorio.local

import com.example.perfil.Resultado
import com.example.perfil.Universidad

interface UniversidadesUseCase {

    suspend fun traerUniversidadesNube(): Resultado<List<Universidad>>

}