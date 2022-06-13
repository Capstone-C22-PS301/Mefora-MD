package com.example.mefora.ui.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentProfileBinding
import com.example.mefora.ui.patient.EditProfilePatientActivity
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.patient.PatientMainViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: PatientMainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.patientData.observe(viewLifecycleOwner) { data ->
                when (data) {
                    is DataResponse.Success -> {
                        val userData = viewModel.getAuth()
                        data.data?.let { patientData ->
                            tvProfileFullName.text = patientData.name
                            tvProfileAddress.text = patientData.address
                            tvProfileName.text = patientData.name
                            tvProfileIdPatient.text = userData.currentUser?.uid.toString()
                            tvProfileEmail.text = userData.currentUser?.email.toString()
                            viewModel.getDoctorData()
                            viewModel.doctorData.observe(viewLifecycleOwner) { data ->
                                when (data) {
                                    is DataResponse.Success -> {
                                        data.data?.let { doctorData ->
                                            tvProfileIdDoctor.text = doctorData.name
                                            btnProfileEdit.setOnClickListener {
                                                Intent(context, EditProfilePatientActivity::class.java).also { intent ->
                                                    intent.putExtra("patientData", patientData)
                                                    intent.putExtra("doctorData", doctorData)
                                                    startActivity(intent)
                                                }
                                            }
                                        }
                                    }
                                    is DataResponse.Failed -> {
                                        Toast.makeText(context, data.msg, Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                    is DataResponse.Failed -> {
                        Toast.makeText(context, data.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}