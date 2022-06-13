package com.example.mefora.ui.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentDoctorBinding
import com.example.mefora.viewmodel.doctor.DoctorMainViewModel


class DoctorFragment : Fragment() {

    private var _binding: FragmentDoctorBinding? = null
    private lateinit var viewModel: DoctorMainViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel =
//            ViewModelProvider(this)[DoctorViewModel::class.java]

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[DoctorMainViewModel::class.java]
        binding.apply {
            viewModel.getDoctorData()
            viewModel.doctorData.observe(viewLifecycleOwner) {
                it.data?.let { data ->
                    aboutDoctorName.text = data.name
                    aboutDoctorSpeciality.text = data.uID
                    aboutTextDoctorAddinfoOriginValue.text = data.address
                    aboutTextDoctorAddinfoAgeValue.text = data.birth
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}