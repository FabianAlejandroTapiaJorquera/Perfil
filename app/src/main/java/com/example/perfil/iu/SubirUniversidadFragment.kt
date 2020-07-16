package com.example.perfil.iu

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.subirDatos.SubirUniversidad
import com.example.perfil.subirDatos.SubirUniversidadBD
import kotlinx.android.synthetic.main.fragment_subir_universidad.*

class SubirUniversidadFragment : DialogFragment() {

    private lateinit var firebase: SubirUniversidadBD
    private lateinit var descartar: Button
    private lateinit var subir: Button
    private lateinit var nombre: EditText
    private lateinit var direccion: EditText
    private lateinit var telefono: EditText

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return crearDialogo()
    }

    private fun crearDialogo(): AlertDialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_subir_universidad, null)
        builder.setView(view)

        descartar = view.findViewById(R.id.descartarUniversidadBTN)
        subir = view.findViewById(R.id.subirUniversidadBTN)
        nombre = view.findViewById(R.id.auNombre)
        direccion = view.findViewById(R.id.auDireccion)
        telefono = view.findViewById(R.id.auTelefono)


       funcionesBotones()

        return builder.create()
    }

    fun funcionesBotones(){

        descartar.setOnClickListener {
            dismiss()
        }

        subir.setOnClickListener {
            firebase = SubirUniversidadBD(SubirUniversidad(Universidad(nombre.text.toString(), "", nombre.text.toString().toLowerCase())))
            firebase.subirUniversidadBD()
            dismiss()
        }
    }
}

