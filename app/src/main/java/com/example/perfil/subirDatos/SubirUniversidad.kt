package com.example.perfil.subirDatos

import com.example.perfil.Universidad
import com.google.firebase.firestore.FirebaseFirestore

class SubirUniversidad(val universidad: Universidad): ISubirUniversidad {
    override fun subirUniversidad() {
        FirebaseFirestore.getInstance().collection("universidades").add(universidad)
    }
}