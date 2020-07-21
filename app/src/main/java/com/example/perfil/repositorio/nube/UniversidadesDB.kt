package com.example.perfil.repositorio.nube

import com.example.perfil.Resultado
import com.example.perfil.Universidad
import com.google.firebase.firestore.*
import kotlinx.coroutines.tasks.await


class UniversidadesDB: IUniversidadesDB {

    val universidades = mutableListOf<Universidad>()
    val query: Query = FirebaseFirestore.getInstance().collection("universidades")

    override suspend fun obtenerUniversidades(): Resultado<List<Universidad>> {
        val universidadessNube = FirebaseFirestore.getInstance().collection("universidades").get().await()
        for(documento in universidadessNube){
            universidades.add(
                Universidad(
                    documento.get("nombre").toString(),
                    documento.get("codigo").toString(),
                    documento.get("logo").toString(),
                    documento.get("direccion").toString()
                )
            )
        }
        return Resultado.Success(universidades)
    }
}