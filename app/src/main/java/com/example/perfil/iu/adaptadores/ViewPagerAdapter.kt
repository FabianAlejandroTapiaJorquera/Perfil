package com.example.perfil.iu.adaptadores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.perfil.iu.navegacionTab.AcademicosFragment
import com.example.perfil.iu.navegacionTab.DatosFragment

class ViewPagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment1 = DatosFragment()
        val fragment2 = AcademicosFragment()
        when(position){
            0 -> { return fragment1 }
            1 -> { return fragment2 }
            else -> { return fragment1 }
        }
    }
}