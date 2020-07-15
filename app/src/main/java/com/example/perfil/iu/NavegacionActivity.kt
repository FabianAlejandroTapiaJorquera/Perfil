package com.example.perfil.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perfil.R
import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.local.UniversidadesUseCaseImplemented
import com.example.perfil.repositorio.nube.UniversidadesDB
import com.example.perfil.viewModel.UniversidadVM
import com.example.perfil.viewModel.UniversidadVMFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navegacion.*

class NavegacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)

        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

    }

}