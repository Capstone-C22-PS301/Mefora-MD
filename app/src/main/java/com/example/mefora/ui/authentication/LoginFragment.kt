package com.example.mefora.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.databinding.FragmentLoginBinding
import com.example.mefora.ui.doctor.HomeDoctorActivity
import com.example.mefora.util.DataResponse
import com.example.mefora.ui.authentication.customview.MyPasswordEditText
import com.example.mefora.viewmodel.AuthenticationViewModel
import com.example.mefora.ui.authentication.customview.MyEmailEditText

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var myPasswordEditText: MyPasswordEditText
    private lateinit var myEmailEditText: MyEmailEditText

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
        signUpLink()
        setupAction()

    }

    private fun signUpLink() {
        val spannableString = SpannableString("Don't have an account? Sign up")
        val applicationContext = activity?.applicationContext
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // commit fragment sign up
                val fragment = RegisterFragment()
                val fragmentManager = activity?.supportFragmentManager
                fragmentManager?.commit {
                    replace(R.id.fragment_container, fragment)
                    addToBackStack(null)
                }
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

    private fun setupAction(){
        var messagePasswordEditText = ""
        var messageEmailEditText = ""
        myPasswordEditText = binding.etPassword
        myEmailEditText = binding.etEmail

        myPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                messagePasswordEditText = myPasswordEditText.error as? String ?: ""
                binding.inputPassword.error = messagePasswordEditText
            }

        })

        myEmailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                messageEmailEditText = myEmailEditText.error as? String ?: ""
                binding.inputEmail.error = messageEmailEditText
            }

        })

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                email.isEmpty() || messageEmailEditText.isNotEmpty() -> {
                    if(email.isEmpty()) {
                        binding.inputEmail.error = getString(R.string.error_empty)
                    } else {
                        binding.inputEmail.error = messageEmailEditText
                    }
                }
                password.isEmpty() || messagePasswordEditText.isNotEmpty() -> {
                    binding.inputEmail.error = null
                    if (password.isEmpty()) {
                        binding.inputPassword.error = getString(R.string.error_empty)
                    } else {
                        binding.inputPassword.error = messagePasswordEditText
                    }
                }
                else -> {
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
                }
            }
        }
    }


}