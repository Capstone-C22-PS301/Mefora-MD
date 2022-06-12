package com.example.mefora.ui.authentication

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.mefora.R
import com.example.mefora.databinding.FragmentRegisterBinding
import com.example.mefora.util.DataResponse
import com.example.mefora.viewmodel.AuthenticationViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var authenticationViewModel: AuthenticationViewModel

lateinit var binding: FragmentRegisterBinding

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        binding.apply {
            btnRegister.setOnClickListener {
                val password = inputPassword.editText?.text.toString()
                val confirmPassword = inputConfirmPassword.editText?.text.toString()
                if (password == confirmPassword) {
                    authenticationViewModel.doDoctorRegister(
                        inputEmail.editText?.text.toString(),
                        inputPassword.editText?.text.toString()
                    )
                } else {
                    inputConfirmPassword.error = "Password not match"
                }
            }
        }

        binding.checkboxDoctor.setOnCheckedChangeListener { _, isChecked ->
            binding.inputDoctorId.visibility = if (isChecked) View.VISIBLE else View.GONE
        }
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


}