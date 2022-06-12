package com.example.mefora.ui.doctor.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.api.model.GetPatientListResponseItem
import com.example.mefora.databinding.FragmentHomeItemBinding
import com.example.mefora.ui.doctor.PatientDetailActivity
import com.example.mefora.ui.doctor.adapter.PatientAdapter
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.doctor.DoctorMainViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentHomeItemBinding
private lateinit var viewModel: DoctorMainViewModel
private lateinit var adapter: PatientAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [HomeItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeItemFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DoctorMainViewModel::class.java]
        viewModel.getPatientList()
        binding.apply {
            viewModel.patientListData.observe(viewLifecycleOwner) {
                it.data?.getPatientListResponse?.let { data ->
                    val dataFilter =
                        (data as List<*>).filterIsInstance<GetPatientListResponseItem>()
                    adapter =
                        PatientAdapter(dataFilter, object : PatientAdapter.OnItemClickListener {
                            override fun onItemClick(item: GetPatientListResponseItem) {
                                Intent(context, PatientDetailActivity::class.java).also { intent ->
                                    startActivity(intent)
                                }
                            }
                        })
                    recyclerView.adapter = adapter
                    dataFilter.forEachIndexed { index, loop ->
                        viewModel.getPatientDiseaseData(loop.patientUid.toString())
                        viewModel.patientData.observe(viewLifecycleOwner) { patient ->
                            when (patient) {
                                is DataResponse.Success -> {
                                    adapter.addData(dataFilter[index])
                                }
                                is DataResponse.Failed -> {
                                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeItemFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}