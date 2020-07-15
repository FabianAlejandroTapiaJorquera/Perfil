package com.example.perfil.repositorio.nube

import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.Universidad
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UniversidadesDB: IUniversidadesDB {

    val universidades = mutableListOf<Universidad>()

    override suspend fun obtenerUniversidades(): Resultado<List<Universidad>> {
        val universidadessNube = FirebaseFirestore.getInstance().collection("universidades").get().await()
        for(documento in universidadessNube){
            universidades.add(Universidad(documento.get("nombre").toString(), documento.get("logo").toString()))
        }
        return Resultado.Success(universidades)
    }
}