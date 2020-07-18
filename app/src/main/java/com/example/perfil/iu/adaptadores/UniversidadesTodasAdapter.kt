package com.example.perfil.iu.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perfil.R

class UniversidadesTodasAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UniversidadesAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.universidades_todas, null, false))
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class UniversidadesAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }
}