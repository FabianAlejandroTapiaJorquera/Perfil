package com.example.perfil.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.perfil.repositorio.local.UniversidadesUseCase

class UniversidadVMFactory(private val universidadUseCase: UniversidadesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UniversidadesUseCase::class.java).newInstance(universidadUseCase)
    }

}