package com.example.mefora.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mefora.repository.AuthenticationRepository

class AuthenticationViewModel: ViewModel(){

    private val repository = AuthenticationRepository()

    fun saveLoginInfo(){
        repository.doLogin()
    }



}