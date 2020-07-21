package com.example.perfil

data class Universidad(val nombre: String, val codigo: String, val logo:String, val direccion: String){
    constructor(): this("", "", "", "")
}