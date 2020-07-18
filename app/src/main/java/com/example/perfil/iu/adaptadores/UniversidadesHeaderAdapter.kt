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
import kotlinx.android.synthetic.main.universidades_favoritas.view.*

class UniversidadesHeaderAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UniversidadesAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.universidades_favoritas, null, false))
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    inner class UniversidadesAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }
}