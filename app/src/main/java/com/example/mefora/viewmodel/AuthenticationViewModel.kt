package com.example.mefora.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.api.model.CreateUser
import com.example.mefora.api.model.CreateUserResponse
import com.example.mefora.api.model.GetUserResponse
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _authenticationRegisterData = MutableLiveData<DataResponse<CreateUserResponse>>()
    val authenticationRegisterData: LiveData<DataResponse<CreateUserResponse>> =
        _authenticationRegisterData

    private val _loadingAuthentication = MutableLiveData<Boolean>()
    val loadingAuthentication: LiveData<Boolean> = _loadingAuthentication

    private val _authenticationLoginData = MutableLiveData<DataResponse<GetUserResponse>>()
    val authenticationLoginData: LiveData<DataResponse<GetUserResponse>> = _authenticationLoginData

    fun doLogin(email: String, password: String) {
        _loadingAuthentication.value = true
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                Log.d("TAG", "doLogin: ${task.isSuccessful}")
                if (task.isSuccessful) {
                    Log.d("TAG", "doLogin: ${firebaseAuth.currentUser?.uid}")
                    firebaseAuth.currentUser?.uid?.let {
                        ApiConfig.getApiService().getUser(it)
                            .enqueue(object : Callback<GetUserResponse> {
                                override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                                    _authenticationLoginData.value =
                                        DataResponse.Failed(t.message.toString())
                                    _loadingAuthentication.value = false
                                    Log.d("TAG", "onResponse: ${t.message}")
                                }

                                override fun onResponse(
                                    call: Call<GetUserResponse>,
                                    response: Response<GetUserResponse>
                                ) {
                                    _authenticationLoginData.value =
                                        DataResponse.Success(response.body()!!)
                                    _loadingAuthentication.value = false
                                    Log.d("TAG", "onResponse: ${response.raw().code}")

                                }
                            })
                    }
                } else {
                    _authenticationRegisterData.value =
                        DataResponse.Failed(task.exception?.message.toString())
                    Log.d("TAG", "FailLogin: ${task.exception?.message}")
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
                            val dataObject = CreateUser(
                                firebaseAuth.currentUser?.uid,
                                null,
                                null,
                                null,
                                null,
                                false
                            )
                            ApiConfig.getApiService().createUser(dataObject).enqueue(object :
                                Callback<CreateUserResponse> {
                                override fun onFailure(
                                    call: Call<CreateUserResponse>,
                                    t: Throwable
                                ) {
                                    _authenticationRegisterData.value =
                                        DataResponse.Failed(t.message.toString())
                                }

                                override fun onResponse(
                                    call: Call<CreateUserResponse>,
                                    response: Response<CreateUserResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        _authenticationRegisterData.value =
                                            response.body()?.let { data ->
                                                DataResponse.Success(data)
                                            }
                                    } else {
                                        _authenticationRegisterData.value =
                                            DataResponse.Failed(response.message())
                                    }
                                }
                            })
                        }
                    } else {
                        _authenticationRegisterData.value =
                            DataResponse.Failed(it.exception?.message.toString())
                    }
                }
            } else {
                _authenticationRegisterData.value =
                    DataResponse.Failed(task.exception?.message.toString())
            }
            _loadingAuthentication.value = false
        }
    }

    fun doDoctorRegister(email: String, password: String) {
        _loadingAuthentication.value = true
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.uid?.let { uid ->
                        val dataObject = CreateUser(
                            firebaseAuth.currentUser?.uid,
                            null,
                            null,
                            null,
                            null,
                            true
                        )
                        val client = ApiConfig.getApiService().createUser(dataObject)
                        client.enqueue(object :
                            Callback<CreateUserResponse> {
                            override fun onResponse(
                                call: Call<CreateUserResponse>,
                                response: Response<CreateUserResponse>
                            ) {
                                if (response.isSuccessful) {
                                    _authenticationRegisterData.value =
                                        response.body()?.let { data ->
                                            DataResponse.Success(data)
                                        }
                                } else {
                                    _authenticationRegisterData.value =
                                        DataResponse.Failed(response.message())
                                }
                            }

                            override fun onFailure(
                                call: Call<CreateUserResponse>,
                                t: Throwable
                            ) {
                                _authenticationRegisterData.value =
                                    DataResponse.Failed(t.message.toString())
                            }

                        })
                    }
                } else {
                    _authenticationRegisterData.value =
                        DataResponse.Failed(task.exception?.message.toString())
                }
                _loadingAuthentication.value = false
            }
    }
}