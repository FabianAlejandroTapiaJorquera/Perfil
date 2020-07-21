package com.example.perfil.iu.navegacionPrincipal

import android.graphics.Insets.add
import android.os.Bundle
import android.system.Os.remove
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter

import com.example.perfil.R
import com.example.perfil.Resultado
import com.example.perfil.Universidad
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.iu.UniversidadPerfilFragment
import com.example.perfil.iu.adaptadores.UniversidadesAdapter
import com.example.perfil.iu.adaptadores.UniversidadesHeaderAdapter
import com.example.perfil.iu.adaptadores.UniversidadesTodasAdapter
import com.example.perfil.repositorio.local.UniversidadesUseCaseImplemented
import com.example.perfil.repositorio.nube.UniversidadesDB
import com.example.perfil.viewModel.UniversidadVM
import com.example.perfil.viewModel.UniversidadVMFactory
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment(), UniversidadesAdapter.OnUniversidadClickListener {

    private lateinit var fragment: Fragment

    private val universidadesAdapter by lazy { UniversidadesAdapter(requireContext(), this) }
    private val universidadesHeaderAdapter by lazy { UniversidadesHeaderAdapter(requireContext()) }

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
        val mergeAdapter = MergeAdapter(universidadesHeaderAdapter, universidadesAdapter)
        recyclerView.adapter = mergeAdapter

        observarDatos()
        funcionesBotones()

        layoutRefrescante.setOnRefreshListener {
            refrescarUI()
            layoutRefrescante.isRefreshing = false
        }


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
                        universidadesAdapter.setearUniversidades(array)
                        universidadesAdapter.notifyDataSetChanged()
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
    fun refrescarUI(){
        fragment = InicioFragment()
        parentFragmentManager.beginTransaction().add(R.id.nav_host_fragment,fragment).remove(this).commit()

    }
}