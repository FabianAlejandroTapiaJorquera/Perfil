package com.example.perfil.iu.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perfil.R
import com.example.perfil.Universidad
import kotlinx.android.synthetic.main.universidades.view.*

class UniversidadesAdapter(
    private val context: Context,
    private val universidades: ArrayList<Universidad>,
    private val itemClickListener: OnUniversidadClickListener
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnUniversidadClickListener{
        fun OnImagenClick(logo: String)
        fun OnItemClick(universidad: Universidad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UniversidadesAdapteriVewHolder(LayoutInflater.from(context).inflate(R.layout.universidades, null, false))
    }

    override fun getItemCount(): Int {
        return universidades.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is UniversidadesAdapteriVewHolder ->{
                holder.setearUniversidades(universidades.get(position))
            }
        }
    }

    inner class UniversidadesAdapteriVewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun setearUniversidades(universidad: Universidad){
            itemView.setOnClickListener { itemClickListener.OnItemClick(universidad) }
            itemView.imagenUniversidad.setOnClickListener { itemClickListener.OnImagenClick(universidad.logo) }
            itemView.nombreUniversidad.text = universidad.nombre
            Glide.with(context).load(universidad.logo).into(itemView.imagenUniversidad)
            itemView.direccionUniversidad.text = universidad.direccion

        }
    }
}