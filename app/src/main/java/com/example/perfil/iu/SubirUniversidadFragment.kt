package com.example.perfil.iu

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.subirDatos.SubirUniversidad
import com.example.perfil.subirDatos.SubirUniversidadBD
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_subir_universidad.*
import java.util.*
import kotlin.random.Random

class SubirUniversidadFragment : DialogFragment() {

    private val letras = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    private val codigo by lazy { generarCodigo() }

    private val GALLERY_PICK_INTENT by lazy { 1 }

    private lateinit var firebase: SubirUniversidadBD
    private lateinit var descartar: Button
    private lateinit var subir: Button
    private lateinit var nombre: EditText
    private lateinit var direccion: EditText
    private lateinit var telefono: EditText
    private lateinit var rutaImagen: TextView
    private lateinit var foto: ImageButton

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
        foto = view.findViewById(R.id.auFoto)
        rutaImagen = view.findViewById(R.id.auFotoRuta)


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
            firebase = SubirUniversidadBD(SubirUniversidad(Universidad(nombre.text.toString(), "", codigo, auDireccion.text.toString())))
            firebase.subirUniversidadBD()
            dismiss()
        }

        foto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_PICK_INTENT)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_PICK_INTENT && resultCode == RESULT_OK) {
            val uri = data?.data
            val referencia = FirebaseStorage.getInstance().getReference().child("fotos").child(codigo)
            referencia.putFile(uri!!)
            rutaImagen.text = uri.path
        }
    }
}

