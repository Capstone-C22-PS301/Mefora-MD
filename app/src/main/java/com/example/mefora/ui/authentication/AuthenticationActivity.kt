package com.example.mefora.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mefora.R
import com.example.mefora.viewmodel.AuthenticationViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationActivity : AppCompatActivity() {
    private val authenticationViewModel by viewModels<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

//        if (auth.currentUser != null) {
//            authenticationViewModel.navigateToHome()
//        }

        supportFragmentManager.beginTransaction()
            .add(R.id.container, LoginFragment())
            .commit()
    }
}