package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter

import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.iu.ImagenFragmentDirections
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.iu.adaptadores.UniversidadesAdapter
import com.example.perfil.iu.adaptadores.UniversidadesHeaderAdapter
import com.example.perfil.iu.adaptadores.UniversidadesTodasAdapter
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment(), UniversidadesAdapter.OnUniversidadClickListener {

    private val subirUniversidadFragment by lazy { SubirUniversidadFragment() }
    private val universidades by lazy { ArrayList<Universidad>() }
    private val universidadesFavoritas by lazy { ArrayList<Universidad>() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_inicio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        llenarUniversidades()
        llenarUniversidadesFavoritas()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        val universidadesFavoritasAdapter = UniversidadesAdapter(requireContext(), universidadesFavoritas, this)
        val universidadesRestoAdapter = UniversidadesAdapter(requireContext(), universidades, this)
        val universidadesHeaderAdapter = UniversidadesHeaderAdapter(requireContext())
        val universidadesTodasAdapter = UniversidadesTodasAdapter(requireContext())
        val mergeAdapter = MergeAdapter(universidadesHeaderAdapter, universidadesFavoritasAdapter, universidadesTodasAdapter, universidadesRestoAdapter)
        recyclerView.adapter = mergeAdapter
        funcionesBotones()


    }

    override fun OnImagenClick(logo: String) {
        val accion = InicioFragmentDirections.pasarImagenIFIF(logo)
        findNavController().navigate(accion)

    }

    override fun OnItemClick(universidad: Universidad) {
        Toast.makeText(context, "AUN NO HACE NADA", Toast.LENGTH_SHORT).show()
    }

    fun funcionesBotones(){
        //OnClick
        agregarUniversidadFBTN.setOnClickListener {
            subirUniversidadFragment.show(childFragmentManager, "Subir Universidad")
        }
    }
    fun llenarUniversidades(){
        universidades.add(Universidad("U de Chile", "https://www.uchile.cl/image/f159277-0-h.jpeg?3144", "123446", "Las Palmeras"))
        universidades.add(Universidad("UC", "https://lh3.googleusercontent.com/proxy/1TA6QpGubyn1DsoM_aWPZkt-AlotXlCw3AyHSgt1eZ-jHJVujUADexdAgG4whZyX95nBmBjEdGEBbxOOC5iF4CeKSzw19V4TKymav2bpRgHBSQl-LsI4XpY", "122455", "San Joaquín"))
    }
    fun llenarUniversidadesFavoritas(){
        universidadesFavoritas.add(Universidad("Utem", "https://upload.wikimedia.org/wikipedia/commons/4/43/Logo_UTEM.png", "123456", "Las Palmeras"))
        universidadesFavoritas.add(Universidad("Usach", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Logo-Color-Usach-Web.jpg/1200px-Logo-Color-Usach-Web.jpg", "123455", "Estación Central"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        universidades.clear()
        universidadesFavoritas.clear()
    }
}