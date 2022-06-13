package com.example.mefora.ui.patient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mefora.databinding.ActivityEditProfilePatientBinding

class EditProfilePatientActivity : AppCompatActivity() {
    private var binding: ActivityEditProfilePatientBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilePatientBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}