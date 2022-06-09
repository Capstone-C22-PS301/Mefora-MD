package com.example.mefora.ui.authentication

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
import com.example.mefora.R
import com.example.mefora.databinding.FragmentLoginBinding
import kotlin.math.sign

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        signUpLink()
        return binding.root
    }

    private fun signUpLink(){
        val spannableString = SpannableString("Don't have an account? Sign up")
        val applicationContext = activity?.applicationContext
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                Toast.makeText(applicationContext, "Masuk", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(applicationContext!!, R.color.light_pink_button)
            }
        }
        spannableString.setSpan(clickableSpan, 23, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.textRegister.text = spannableString
        binding.textRegister.movementMethod = LinkMovementMethod.getInstance()

    }

}