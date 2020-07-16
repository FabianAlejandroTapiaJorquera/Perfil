package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

import com.example.perfil.R
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.subirDatos.SubirUniversidad
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment() {

    private val subirUniversidadFragment by lazy { SubirUniversidadFragment() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //OnCLick
        agregarUniversidadFBTN.setOnClickListener {
            subirUniversidadFragment.show(childFragmentManager, "Subir Universidad")
        }

    }


}