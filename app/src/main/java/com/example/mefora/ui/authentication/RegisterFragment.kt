package com.example.mefora.ui.authentication

import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.databinding.FragmentRegisterBinding
import com.example.mefora.viewmodel.AuthenticationViewModel
import com.example.mefora.ui.authentication.customview.MyEmailEditText
import com.example.mefora.ui.authentication.customview.MyPasswordEditText

class RegisterFragment : Fragment() {

    private lateinit var authenticationViewModel: AuthenticationViewModel
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var myPasswordEditText: MyPasswordEditText
    private lateinit var myConfirmPasswordEditText: MyPasswordEditText
    private lateinit var myEmailEditText: MyEmailEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]

        binding.checkboxDoctor.setOnCheckedChangeListener { _, isChecked ->
            binding.inputDoctorId.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
        setupAction()
        signInLink()
    }

    private fun signInLink() {
        val spannableString = SpannableString("Already have an account? Sign in")
        val applicationContext = activity?.applicationContext
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // commit fragment sign up
                val fragment = LoginFragment()
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
            25,
            spannableString.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        binding.tvToLogin.text = spannableString
        binding.tvToLogin.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupAction() {
        var messagePasswordEditText = ""
        var messageEmailEditText = ""
        var messageConfirmPasswordEditText = ""
        myPasswordEditText = binding.etPassword
        myEmailEditText = binding.etEmail
        myConfirmPasswordEditText = binding.etConfirmPassword
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

        myConfirmPasswordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                if (myPasswordEditText.text.toString() != myConfirmPasswordEditText.text.toString()) {
                    messageConfirmPasswordEditText = "Password not match"
                    binding.inputConfirmPassword.error = messageConfirmPasswordEditText
                } else {
                    messageConfirmPasswordEditText = ""
                    binding.inputConfirmPassword.error = messageConfirmPasswordEditText
                }


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

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val cpassword = binding.etConfirmPassword.text.toString()
            when {
                email.isEmpty() || messageEmailEditText.isNotEmpty() -> {
                    binding.inputEmail.error = null
                    if(email.isEmpty()) {
                        binding.inputEmail.error = getString(R.string.error_empty)
                    } else {
                        binding.inputEmail.error = messageEmailEditText
                    }
                }
                password.isEmpty() || messagePasswordEditText.isNotEmpty() || messageConfirmPasswordEditText.isNotEmpty() -> {
                    binding.inputEmail.error = null
                    if (password.isEmpty()) {
                        binding.inputPassword.error = getString(R.string.error_empty)
                    } else {
                        binding.inputPassword.error = messagePasswordEditText
                    }
                }
                cpassword.isEmpty() || messageConfirmPasswordEditText.isNotEmpty() -> {
                    if (cpassword.isEmpty()) {
                        binding.inputConfirmPassword.error = getString(R.string.error_empty)
                    }
                    if (cpassword != password) {
                        binding.inputConfirmPassword.error = getString(R.string.error_password_not_match)
                    }
                    else {
                        binding.inputConfirmPassword.error = messageConfirmPasswordEditText
                    }
                }
                else -> {
                    authenticationViewModel.doDoctorRegister(
                        email,
                        password
                    )
                }
            }
        }
    }


}