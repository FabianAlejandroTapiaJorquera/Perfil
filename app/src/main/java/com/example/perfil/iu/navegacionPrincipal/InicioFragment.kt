package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.perfil.R
import com.example.perfil.repositorio.DialogoAgregarUniversidad
import kotlinx.android.synthetic.main.fragment_inicio.*

class InicioFragment : Fragment() {

    private val agregarPop by lazy { DialogoAgregarUniversidad() }

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
        agregarUniversidad.setOnClickListener( View.OnClickListener {
            agregarPop.show(childFragmentManager, "Pop")
        } )

    }


}