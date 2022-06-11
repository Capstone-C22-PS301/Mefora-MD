package com.example.mefora.ui.patient.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mefora.ui.patient.viewpager.ProhibitedFoodFragment
import com.example.mefora.ui.patient.viewpager.RecommendFoodFragment
import com.example.mefora.ui.patient.viewpager.RoutineFoodFragment

class SectionsPagerAdapter(fragment: Fragment ) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ProhibitedFoodFragment()
            1 -> fragment = RecommendFoodFragment()
            2 -> fragment = RoutineFoodFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }

}