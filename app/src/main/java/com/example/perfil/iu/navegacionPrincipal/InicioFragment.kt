package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter

import com.example.perfil.R
import com.example.perfil.Resultado
import com.example.perfil.Universidad
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.iu.adaptadores.UniversidadesAdapter
import com.example.perfil.iu.adaptadores.UniversidadesHeaderAdapter
import com.example.perfil.iu.adaptadores.UniversidadesTodasAdapter
import com.example.perfil.repositorio.local.UniversidadesUseCaseImplemented
import com.example.perfil.repositorio.nube.UniversidadesDB
import com.example.perfil.viewModel.UniversidadVM
import com.example.perfil.viewModel.UniversidadVMFactory
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment(), UniversidadesAdapter.OnUniversidadClickListener {

    private val universidadesFavoritasAdapter by lazy { UniversidadesAdapter(requireContext(), this) }
    private val universidadesRestoAdapter by lazy { UniversidadesAdapter(requireContext(), this) }

    private val universidadViewModel by lazy {
        ViewModelProvider(this, UniversidadVMFactory(UniversidadesUseCaseImplemented(UniversidadesDB()))).get(UniversidadVM::class.java)
    }

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

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        val universidadesHeaderAdapter = UniversidadesHeaderAdapter(requireContext())
        val universidadesTodasAdapter = UniversidadesTodasAdapter(requireContext())
        val mergeAdapter = MergeAdapter(universidadesHeaderAdapter, universidadesFavoritasAdapter, universidadesTodasAdapter, universidadesRestoAdapter)
        recyclerView.adapter = mergeAdapter

        observarDatos()

        funcionesBotones()


    }

    override fun OnImagenClick(logo: String) {
        val accion = InicioFragmentDirections.pasarImagenIFIF(logo)
        findNavController().navigate(accion)

    }

    override fun OnItemClick(universidad: Universidad) {
        val universidad = arrayOf(universidad.nombre, universidad.codigo, universidad.logo, universidad.direccion)
        val accion = InicioFragmentDirections.actionInicioFragmentToUniversidadPerfilFragment(universidad)
        findNavController().navigate(accion)
    }

    fun funcionesBotones(){
        //OnClick
        agregarUniversidadFBTN.setOnClickListener {
            subirUniversidadFragment.show(childFragmentManager, "Subir Universidad")
        }
    }
    fun observarDatos(){
        universidadViewModel.universidades.observe(viewLifecycleOwner, Observer { resultado->
            when(resultado){
                is Resultado.Loading -> {
                    showProgressBar()
                }
                is Resultado.Success -> {
                    if(resultado.dato.isEmpty())
                        sinUniversidades.visibility = View.VISIBLE
                    else {
                        val array = ArrayList<Universidad>()
                        array.addAll(resultado.dato)
                        universidadesFavoritasAdapter.setearUniversidades(array)
                        universidadesRestoAdapter.setearUniversidades(array)
                        universidadesFavoritasAdapter.notifyDataSetChanged()
                        universidadesRestoAdapter.notifyDataSetChanged()
                    }
                    hideProgressBar()
                }
                is Resultado.Error -> {
                    Toast.makeText(requireContext(), "No se pudo conectar a la base de datos ${resultado.exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        universidades.clear()
    }

    fun showProgressBar(){
        progressBar.visibility = View.VISIBLE
    }
    fun hideProgressBar(){
        progressBar.visibility = View.GONE
    }
}