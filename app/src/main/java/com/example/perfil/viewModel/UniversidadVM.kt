package com.example.perfil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.local.UniversidadesUseCase
import kotlinx.coroutines.Dispatchers

class UniversidadVM(casoUsoUniversidad: UniversidadesUseCase): ViewModel() {

    //Obteniendo universidades desde cloud Firestore
    val universidades = liveData(Dispatchers.IO) {
        emit(Resultado.Loading())
        try {
            emit(casoUsoUniversidad.traerUniversidadesNube())
        }catch (e: Exception){
            emit(Resultado.Error(e))
        }
    }
}