package com.example.mefora.ui.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.api.model.GetUserResponse
import com.example.mefora.databinding.ActivityEditProfilePatientBinding
import com.example.mefora.viewmodel.patient.PatientMainViewModel


class EditProfilePatientActivity : AppCompatActivity() {

    private lateinit var viewModel: PatientMainViewModel
    private lateinit var binding: ActivityEditProfilePatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilePatientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = viewModel.getAuth()
        viewModel = ViewModelProvider(this)[PatientMainViewModel::class.java]
        val patientData = this.intent.getParcelableExtra<GetUserResponse>("patientData")
        binding.apply {
            profileEmail.text = auth.currentUser?.email
            profileName.text = patientData?.name
            profileIdPatient.text = patientData?.uID
            buttonCancel.setOnClickListener {
                finish()
            }
            buttonEdit.setOnClickListener {
                val userData = GetUserResponse(
                    patientData?.uID,
                    patientData?.createdAt,
                    etAddress.text.toString(),
                    etFullName.text.toString(),
                    etNickname.text.toString(),
                    etBirth.text.toString(),
                    true,
                    patientData?.updatedAt,
                )
                viewModel.editProfile(userData)
            }
        }
    }
}