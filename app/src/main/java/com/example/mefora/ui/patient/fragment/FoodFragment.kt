package com.example.mefora.ui.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mefora.R
import com.example.mefora.databinding.FragmentFoodBinding
import com.example.mefora.ui.patient.DoctorScanActivity
import com.example.mefora.ui.patient.adapter.FoodAdapter
import com.example.mefora.ui.patient.adapter.SectionsPagerAdapter
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.patient.PatientMainViewModel
import com.google.android.material.tabs.TabLayoutMediator


class FoodFragment : Fragment() {

    private lateinit var viewModel: PatientMainViewModel
    private var _binding: FragmentFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FoodAdapter

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnScan.setOnClickListener {
                Intent(context, DoctorScanActivity::class.java).also{
                    startActivity(it)
                }
            }
            viewModel.getDiseaseData()
            viewModel.diseaseData.observe(viewLifecycleOwner) { diseaseData ->
                when (diseaseData) {
                    is DataResponse.Success -> {
                        diseaseData.data?.getDiseaseResponse?.let { data ->
                            val diseaseString = data[0]?.diseaseName
                            data.forEach { loop ->
                                diseaseString?.plus(" ${loop?.diseaseName}")
                            }
                            viewModel.getFoodData(diseaseString.toString())
                            viewModel.foodData.observe(viewLifecycleOwner) { adapterData ->
                                adapterData.data?.predictions?.get(0)?.output2?.let { data ->
                                    val dataFilter = (data as List<*>).filterIsInstance<String>()
                                    adapter = FoodAdapter(dataFilter)
                                    rcFood.layoutManager = LinearLayoutManager(context)
                                    rcFood.adapter = adapter
                                }
                            }
                        }
                    }
                    is DataResponse.Failed -> {
                        Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
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