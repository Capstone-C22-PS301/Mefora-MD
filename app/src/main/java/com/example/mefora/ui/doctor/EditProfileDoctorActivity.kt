package com.example.mefora.ui.doctor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.api.model.GetUserResponse
import com.example.mefora.databinding.ActivityEditProfileDoctorBinding
import com.example.mefora.viewmodel.doctor.DoctorMainViewModel

class EditProfileDoctorActivity : AppCompatActivity() {

    private lateinit var viewModel: DoctorMainViewModel
    private lateinit var binding: ActivityEditProfileDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DoctorMainViewModel::class.java]
        val data = this.intent.getParcelableExtra<GetUserResponse>("data")
        val auth = viewModel.getAuth()
        binding.apply {
            profileName.text = data?.name
            profileIdDoctor.text = data?.uID
            profileEmail.text = auth.currentUser?.email
            buttonCancel.setOnClickListener {
                finish()
            }
            buttonEdit.setOnClickListener {
                val userData = GetUserResponse(
                    data?.uID,
                    data?.createdAt,
                    etDoctorAddress.text.toString(),
                    etDoctorName.text.toString(),
                    etDoctorNickname.text.toString(),
                    etDoctorBirth.text.toString(),
                    true,
                    data?.updatedAt,
                    )
                viewModel.editUserData(userData)
            }
        }
    }

}