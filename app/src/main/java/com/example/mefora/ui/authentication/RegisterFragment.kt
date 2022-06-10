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
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.example.mefora.R
import com.example.mefora.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        signInLink()
        return binding.root
    }

    private fun signInLink(){
        val spannableString = SpannableString("Already have an account? Sign in")
        val applicationContext = activity?.applicationContext
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                //replace this fragment with sign up fragment
                val loginFragment = LoginFragment()
                val fragmentManager = activity?.supportFragmentManager
                fragmentManager?.commit {
                    replace(R.id.fragment_container, loginFragment)
                }


            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = ContextCompat.getColor(applicationContext!!, R.color.light_pink_button)
            }
        }
        spannableString.setSpan(clickableSpan, 25, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        binding.textSignin.text = spannableString
        binding.textSignin.movementMethod = LinkMovementMethod.getInstance()

    }

}