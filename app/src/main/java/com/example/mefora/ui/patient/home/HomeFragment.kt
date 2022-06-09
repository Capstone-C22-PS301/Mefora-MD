package com.example.mefora.ui.patient.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentHomeBinding
import com.example.mefora.ui.patient.food.FoodViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            binding.textHome.text = it
//        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}