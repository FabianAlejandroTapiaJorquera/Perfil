package com.example.perfil.repositorio.local

import com.example.perfil.Resultado
import com.example.perfil.Universidad
import com.example.perfil.repositorio.nube.IUniversidadesDB

class UniversidadesUseCaseImplemented(private val universidadDB: IUniversidadesDB): UniversidadesUseCase {

    override suspend fun traerUniversidadesNube(): Resultado<List<Universidad>> {
        return universidadDB.obtenerUniversidades()
    }

}