package com.example.perfil.iu.navegacionPrincipal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.MergeAdapter
import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.iu.SubirUniversidadFragment
import com.example.perfil.iu.adaptadores.UniversidadFirestoreRecyclerAdapter
import com.example.perfil.iu.adaptadores.UniversidadesAdapter
import com.example.perfil.iu.adaptadores.UniversidadesHeaderAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_inicio.*


class InicioFragment : Fragment(), UniversidadesAdapter.OnUniversidadClickListener {

    val query = FirebaseFirestore.getInstance().collection("universidades")
    var options: FirestoreRecyclerOptions<Universidad> = FirestoreRecyclerOptions.Builder<Universidad>()
        .setQuery(query, Universidad::class.java)
        .build()

    private val universidadesAdapter by lazy { UniversidadFirestoreRecyclerAdapter(requireContext(), this, options) }
    private val universidadesHeaderAdapter by lazy { UniversidadesHeaderAdapter(requireContext()) }

   private val subirUniversidadFragment by lazy { SubirUniversidadFragment() }


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
    override fun onStart() {
        super.onStart()
        universidadesAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        universidadesAdapter.stopListening()
    }
}