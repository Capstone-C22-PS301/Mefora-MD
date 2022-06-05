package com.example.mefora.ui.doctor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.databinding.FragmentDoctorBinding
import com.example.mefora.databinding.FragmentFoodBinding
import com.example.mefora.ui.food.FoodViewModel

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
            ViewModelProvider(this)[FoodViewModel::class.java]

        _binding = FragmentDoctorBinding.inflate(inflater, container, false)
        val view = binding.root

//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            binding.textDoctor.text = it
//        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}