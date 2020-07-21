package com.example.perfil.iu.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perfil.R
import com.example.perfil.Universidad
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.synthetic.main.universidades.view.*

class UniversidadFirestoreRecyclerAdapter(
    private val context: Context,
    private val itemClickListener: UniversidadesAdapter.OnUniversidadClickListener,
    options: FirestoreRecyclerOptions<Universidad>
): FirestoreRecyclerAdapter<Universidad, UniversidadFirestoreRecyclerAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.universidades, null, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, universidad: Universidad) {
        when(holder){
            is UniversidadFirestoreRecyclerAdapter.ViewHolder ->{
                holder.setearUniversidades(universidad)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setearUniversidades(universidad: Universidad){
            itemView.setOnClickListener { itemClickListener.OnItemClick(universidad) }
            itemView.imagenUniversidad.setOnClickListener { itemClickListener.OnImagenClick(universidad.logo) }
            itemView.nombreUniversidad.text = universidad.nombre

            if(universidad.logo != "")
                Glide.with(context).load(universidad.logo).into(itemView.imagenUniversidad)
            itemView.direccionUniversidad.text = universidad.direccion

        }

    }
}