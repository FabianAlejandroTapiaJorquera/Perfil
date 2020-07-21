package com.example.perfil.subirDatos

class SubirUniversidadBD(private val universidad: ISubirUniversidad){
    fun subirUniversidadBD(){
        universidad.subirUniversidad()
    }
    fun actualizarImagenPerfil(url: String){
        universidad.actualizarLogo(url)
    }
    fun actualizarUniversidad(){
        universidad.actualizarUniversidad()
    }
    fun actualizarUniversidadHashMap(codigo: String, map: HashMap<String, String>){
        universidad.subirUniversidadHashMap(map, codigo)
    }
}