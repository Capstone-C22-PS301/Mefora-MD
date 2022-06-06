package com.example.mefora.ui.patient.food

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.databinding.FragmentFoodBinding


class FoodFragment : Fragment() {

    private var _binding: FragmentFoodBinding? = null

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

        _binding = FragmentFoodBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.textNoscan.visibility = View.GONE

//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            binding.textFood.text = it
//        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}