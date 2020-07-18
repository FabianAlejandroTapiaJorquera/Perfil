package com.example.perfil.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.perfil.R
import kotlinx.android.synthetic.main.activity_navegacion.*

class NavegacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)

        val navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id){
                R.id.inicioFragment -> showBottomNav()
                R.id.chatFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }

    }

    private fun showBottomNav() {
        bottomNavigationView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        bottomNavigationView.visibility = View.GONE

    }

}