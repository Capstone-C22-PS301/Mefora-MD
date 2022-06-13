package com.example.mefora.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mefora.R
import com.example.mefora.databinding.FragmentFoodBinding
import com.example.mefora.ui.patient.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textNoscan.visibility = View.GONE


//        initViewPager()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    private fun initViewPager(){
//        val sectionsPagerAdapter = SectionsPagerAdapter(this)
//        binding.pager.adapter = sectionsPagerAdapter
//        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//        (activity as AppCompatActivity).supportActionBar!!.elevation = 0f
//    }

    companion object{
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//            R.string.tab_text_food_1,
//            R.string.tab_text_food_2,
//            R.string.tab_text_food_3
//        )
    }
}