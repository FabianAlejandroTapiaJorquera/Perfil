package com.example.perfil.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.perfil.R
import com.example.perfil.repositorio.Resultado
import com.example.perfil.repositorio.local.UniversidadesUseCaseImplemented
import com.example.perfil.repositorio.nube.UniversidadesDB
import com.example.perfil.viewModel.UniversidadVM
import com.example.perfil.viewModel.UniversidadVMFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val universidadViewModel by lazy {
        ViewModelProvider(this,
            UniversidadVMFactory(
                UniversidadesUseCaseImplemented(
                    UniversidadesDB()
                )
            )
        ).get(
            UniversidadVM::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observarUniversidades()
    }

    fun observarUniversidades(){
        universidadViewModel.universidades.observe(this, Observer { resultado->
            when(resultado){
                is Resultado.Loading -> {
                    showProgressBar()
                }
                is Resultado.Success -> {
                    resultado.dato.forEach { universidad -> textView.text = universidad.nombre }
                    hideProgressBar()
                }
                is Resultado.Error -> {
                    Toast.makeText(this, "No se pudo conectar a la base de datos ${resultado.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }
}