package com.example.perfil

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.perfil.subirDatos.SubirUniversidad
import kotlinx.android.synthetic.main.agregar_universidad.*


class DialogoAgregarUniversidad(): DialogFragment() {

    private lateinit var firebase: SubirUniversidad

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            builder.setView(inflater.inflate(R.layout.agregar_universidad, null))
                .setPositiveButton("Agregar",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
                .setNegativeButton("Descartar",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}