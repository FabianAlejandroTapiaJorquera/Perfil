package com.example.perfil.subirDatos

interface ISubirUniversidad {
    fun subirUniversidad()
    fun subirUniversidadHashMap(map: HashMap<String, String>, codigo: String)
    fun actualizarUniversidad()
    fun actualizarLogo(nuevaURL: String)
}