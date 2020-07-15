package com.example.perfil.repositorio.local

import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.Universidad
import com.example.perfil.repositorio.nube.IUniversidadesDB

class UniversidadesUseCaseImplemented(private val universidadDB: IUniversidadesDB): UniversidadesUseCase {

    override suspend fun traerUniversidadesNube(): Resultado<List<Universidad>> {
        return universidadDB.obtenerUniversidades()
    }

}