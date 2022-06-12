package com.example.mefora.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.databinding.FragmentLoginBinding
import com.example.mefora.ui.doctor.HomeDoctorActivity
import com.example.mefora.ui.doctor.fragments.DoctorHomeFragment
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.AuthenticationViewModel
import retrofit2.Response
import kotlin.math.sign

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.btnLogin.setOnClickListener {
            viewModel.doLogin(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            viewModel.authenticationData.observe(viewLifecycleOwner) {
                when (it) {
                    is DataResponse.Success -> {
                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                        Intent(context, HomeDoctorActivity::class.java).also { intent ->
                            startActivity(intent)
                        }
                    }
                    is DataResponse.Failed -> {
                        Toast.makeText(context, it.msg, Toast.LENGTH_LONG).show()
                    }
                }
            }
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.replace(R.id.fragment_container, HomeDoctorActivity())
//                ?.addToBackStack(null)
//                ?.commit()
        }
        signUpLink()
    }

    private fun signUpLink() {
        val spannableString = SpannableString("Don't have an account? Sign up")
        val applicationContext = activity?.applicationContext
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, RegisterFragment())
                    .addToBackStack(null)
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(applicationContext!!, R.color.light_pink_button)
            }
        }
        spannableString.setSpan(
            clickableSpan,
            23,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.textRegister.text = spannableString
        binding.textRegister.movementMethod = LinkMovementMethod.getInstance()

    }

}