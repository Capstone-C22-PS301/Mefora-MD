package com.example.mefora.ui.patient.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentProfileBinding
import com.example.mefora.ui.patient.EditProfilePatientActivity
import com.example.mefora.viewmodel.patient.PatientMainViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonEdit.setOnClickListener{
            val intent = Intent(activity, EditProfilePatientActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}