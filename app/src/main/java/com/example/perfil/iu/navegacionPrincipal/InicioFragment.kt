package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter

import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.iu.adaptadores.UniversidadesAdapter
import com.example.perfil.iu.adaptadores.UniversidadesFavoritasAdapter
import com.example.perfil.iu.adaptadores.UniversidadesTodasAdapter
import com.example.perfil.subirDatos.SubirUniversidad
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment() {

    private val subirUniversidadFragment by lazy { SubirUniversidadFragment() }
    private val universidades by lazy { ArrayList<Universidad>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llenarUniversidades()

        recyclerView.layoutManager = LinearLayoutManager(context)
        val universidadesAdapter = UniversidadesAdapter(requireContext(), universidades)
        val universidadesFavoritasAdapter = UniversidadesFavoritasAdapter(requireContext())
        val universidadesTodasAdapter = UniversidadesTodasAdapter(requireContext())
        val mergeAdapter = MergeAdapter(universidadesFavoritasAdapter, universidadesAdapter, universidadesTodasAdapter)
        //Crear nuevo adapter para las universidades favoritas!!
        recyclerView.adapter = mergeAdapter
        funcionesBotones()


    }

    fun funcionesBotones(){
        //OnClick
        agregarUniversidadFBTN.setOnClickListener {
            subirUniversidadFragment.show(childFragmentManager, "Subir Universidad")
        }
    }
    fun llenarUniversidades(){
        universidades.add(Universidad("Utem", "https://upload.wikimedia.org/wikipedia/commons/4/43/Logo_UTEM.png", "123456", "Las Palmeras"))
        universidades.add(Universidad("Usach", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Logo-Color-Usach-Web.jpg/1200px-Logo-Color-Usach-Web.jpg", "123455", "Estación Central"))
    }


}