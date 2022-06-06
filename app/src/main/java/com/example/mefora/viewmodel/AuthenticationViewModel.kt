package com.example.mefora.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _authenticationData = MutableLiveData<DataResponse<String>>()
    val authenticationData: LiveData<DataResponse<String>> = _authenticationData

    private val _loadingAuthentication = MutableLiveData<Boolean>()
    val loadingAuthentication: LiveData<Boolean> = _loadingAuthentication

    fun doLogin(email: String, password: String) {
        _loadingAuthentication.value = true
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.uid?.let {
                        _authenticationData.value = DataResponse.Success(it)
                    }
                } else {
                    _authenticationData.value =
                        DataResponse.Failed(task.exception?.message.toString())
                }
                _loadingAuthentication.value = false
            }
    }

    fun doPatientRegister(email: String, password: String) {
        _loadingAuthentication.value = true
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseAuth.currentUser?.uid?.let { uid ->
                            ApiConfig.getApiService().createUser(uid, false).enqueue(object :
                                Callback<DataResponse<>> {
                                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                                    _authenticationData.value =
                                        DataResponse.Error(t.message.toString())
                                }

                                override fun onResponse(
                                    call: Call<DataResponse>,
                                    response: Response<DataResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        _authenticationData.value = response.body()
                                    } else {
                                        _authenticationData.value =
                                            DataResponse.Error(response.message())
                                    }
                                }

                            })
                        }
                    } else {
                        _authenticationData.value =
                            DataResponse.Error(it.exception?.message.toString())
                    }
                }
            } else {
                _authenticationData.value = DataResponse.Error(task.exception?.message.toString())
            }
            _loadingAuthentication.value = false
        }
    }


    fun doDoctorRegister(email: String, password: String) {
        _loadingAuthentication.value = true
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                firebaseAuth.currentUser?.uid?.let { uid ->
                                    val client = ApiConfig.getApiService().createUser(uid, true)
                                    client.enqueue(object : Callback<DataResponse> {
                                        override fun onResponse(
                                            call: Call<DataResponse>,
                                            response: Response<DataResponse>
                                        ) {
                                            if (response.isSuccessful) {
                                                _authenticationData.value =
                                                    DataResponse.Success(firebaseAuth.currentUser?.uid)
                                            } else {
                                                _authenticationData.value =
                                                    DataResponse.Error(response.message())
                                            }
                                        }
                                        override fun onFailure(
                                            call: Call<DataResponse>,
                                            t: Throwable
                                        ) {
                                            _authenticationData.value =
                                                DataResponse.Error(t.message.toString())
                                        }

                                    })
                                }
                                _authenticationData.value =
                                    DataResponse.Success(firebaseAuth.currentUser?.uid)
                            } else {
                                _authenticationData.value =
                                    DataResponse.Error(it.exception?.message.toString())
                            }
                        }
                }
                _loadingAuthentication.value = false
            }
    }
}