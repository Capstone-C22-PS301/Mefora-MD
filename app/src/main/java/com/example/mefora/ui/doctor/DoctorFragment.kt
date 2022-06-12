package com.example.mefora.ui.doctor

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentDoctorBinding
import com.example.mefora.ui.patient.DoctorScanActivity
import com.example.mefora.viewmodel.patient.PatientMainViewModel

class DoctorFragment : Fragment() {
    private var _binding: FragmentDoctorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this)[PatientMainViewModel::class.java]

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val view = binding.root

        // go to Doctor Scan Activity
        val intent = Intent(activity, DoctorScanActivity::class.java)
        startActivity(intent)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}