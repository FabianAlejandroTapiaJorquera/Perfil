package com.example.perfil.iu

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.perfil.R
import com.example.perfil.Universidad
import com.example.perfil.iu.adaptadores.ViewPagerAdapter
import com.example.perfil.iu.navegacionPrincipal.InicioFragment
import com.example.perfil.subirDatos.SubirUniversidad
import com.example.perfil.subirDatos.SubirUniversidadBD
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_universidad_perfil.*

class UniversidadPerfilFragment : Fragment() {

    private val adaptador by lazy { ViewPagerAdapter(this) }

    //Datos que llegan desde InicioFragment
    val args by navArgs<UniversidadPerfilFragmentArgs>()

    //Constantes
    private val GALLERY_PICK_INTENT by lazy { 1 }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universidad_perfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = adaptador
        TabLayoutMediator(tabLayout, viewPager) { tab: TabLayout.Tab, position: Int ->
           when(position){
               0 -> tab.text = "Información"
               1 -> tab.text = "Academicos"
           }
        }.attach()

        setearLayout()
        funcionesBotones()
    }

    fun setearLayout(){
        //Seteando fragment_universidad_perfil
        pNombreUniversidad.text = args.Universidad[0]
        if(args.Universidad[2] != "")
            Glide.with(this).load(args.Universidad[2]).into(profile_image)
    }
    fun funcionesBotones(){
        //OnClik de los botones de fragment_universidad_perfil
        cambiarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_PICK_INTENT)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_PICK_INTENT && resultCode == RESULT_OK) {
            var url: String
            val uri = data?.data
            val referencia = FirebaseStorage.getInstance().getReference().child("fotos").child(args.Universidad[1])
            referencia.putFile(uri!!).addOnSuccessListener {
                referencia.downloadUrl.addOnCompleteListener {
                    val nuevaImagen = SubirUniversidadBD(SubirUniversidad(Universidad(args.Universidad[0], args.Universidad[1], args.Universidad[2], args.Universidad[3])))
                    url = it.result.toString()
                    nuevaImagen.actualizarImagenPerfil(url)
                    Glide.with(this).load(url).into(profile_image)
                }
            }
        }
    }
}