package com.example.mefora.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mefora.databinding.FragmentHomeBinding
import com.example.mefora.ui.patient.adapter.FoodAdapter
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.patient.PatientMainViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: PatientMainViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: FoodAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PatientMainViewModel::class.java]
        binding.apply {
            tvDate.text = viewModel.getDate()
            viewModel.getPatientData()
            viewModel.patientData.observe(viewLifecycleOwner) {
                when (it) {
                    is DataResponse.Success -> {
                        it.data?.let { data ->
                            tvPatientName.text = data.name
                        }
                    }
                    is DataResponse.Failed -> {
                        Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
                    }
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
    }
}