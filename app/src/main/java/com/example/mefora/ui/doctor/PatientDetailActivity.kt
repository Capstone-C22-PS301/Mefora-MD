package com.example.mefora.ui.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.api.model.GetDiseaseResponseItem
import com.example.mefora.databinding.ActivityPatientDetailBinding
import com.example.mefora.ui.doctor.adapter.DiseasePatientAdapter
import com.example.mefora.ui.doctor.fragments.DoctorPatientPageFragment
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.doctor.DoctorMainViewModel

class PatientDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientDetailBinding
    private lateinit var viewModel: DoctorMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DoctorMainViewModel::class.java]
        viewModel.getPatientData(
            intent.getStringExtra(DoctorPatientPageFragment.PARCEL_EXTRA).toString()
        )
        binding.apply {
            viewModel.patientData.observe(this@PatientDetailActivity) {
                when (it) {
                    is DataResponse.Success -> {
                        it.data?.let { data ->
                            tvTitle.text = data.name
                            tvBirthplace.text = data.birth
                            tvDate.text = data.createdAt
                            viewModel.patientDiseaseData.observe(this@PatientDetailActivity) { response ->
                                when (response) {
                                    is DataResponse.Success -> {
                                        response.data?.let { data ->
                                            val dataList = (data.getDiseaseResponse as List<*>).filterIsInstance<GetDiseaseResponseItem>()
                                            rcDisease.adapter = DiseasePatientAdapter(dataList)
                                        }
                                    }
                                    is DataResponse.Failed -> {
                                        Toast.makeText(this@PatientDetailActivity, "Fetch Data Failed", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                    is DataResponse.Failed -> {
                        it.msg?.let { message ->
                            tvTitle.text = message
                        }
                    }
                }
            }
        }
    }
}