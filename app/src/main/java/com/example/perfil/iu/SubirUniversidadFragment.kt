package com.example.perfil.iu

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.subirDatos.SubirUniversidad
import com.example.perfil.subirDatos.SubirUniversidadBD
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.random.Random

class SubirUniversidadFragment : DialogFragment() {

    private val letras = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val codigo by lazy { generarCodigo() }

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

    fun generarCodigo(): String{
        var codigo = ""
        for(i in 0..10)
            codigo = codigo + letras[Random.nextInt(0, letras.size)]
        return codigo
    }

    fun funcionesBotones(){

        descartar.setOnClickListener {
            dismiss()
        }
        subir.setOnClickListener {
            if(TextUtils.isEmpty(nombre.text)){
                Toast.makeText(requireContext() ,"Debe escribir el nombre de la institución", Toast.LENGTH_SHORT).show()
            }
            if(TextUtils.isEmpty(direccion.text)){
                Toast.makeText(requireContext() ,"Debe escribir la dirección de la institución", Toast.LENGTH_SHORT).show()
            }
            else {
                firebase = SubirUniversidadBD(SubirUniversidad(Universidad(nombre.text.toString(), codigo, "", direccion.text.toString())))
                firebase.subirUniversidadBD()
                Toast.makeText(requireContext(), "Se agrego un nuevo elemento a la base de datos. Por favor desliza hacia abajo para actualizar!", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }
}

