package com.example.perfil.subirDatos

import com.example.perfil.Universidad
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class SubirUniversidad(val universidad: Universidad): ISubirUniversidad {
    override fun subirUniversidad() {
        FirebaseFirestore.getInstance().collection("universidades").document(universidad.codigo).set(universidad)
    }

    override fun actualizarLogo(nuevaURL: String) {
        val logo = hashMapOf("logo" to nuevaURL)
        FirebaseFirestore.getInstance().collection("universidades").document(universidad.codigo).set(logo, SetOptions.merge())
    }
}