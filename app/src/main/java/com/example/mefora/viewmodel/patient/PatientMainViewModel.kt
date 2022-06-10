package com.example.mefora.viewmodel.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.api.model.GetUserResponse
import com.example.mefora.api.model.Instances
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Callback

class PatientMainViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _patientData = MutableLiveData<DataResponse<GetUserResponse>>()
    val patientData: LiveData<DataResponse<GetUserResponse>> = _patientData

    private val _loadingPatientData = MutableLiveData<Boolean>()
    val loadingPatientData: LiveData<Boolean> = _loadingPatientData

    private val _foodData = MutableLiveData<DataResponse<GetUserResponse>>()
    val foodData: LiveData<DataResponse<GetUserResponse>> = _foodData

    private val _loadingFoodData = MutableLiveData<Boolean>()
    val loadingFoodData: LiveData<Boolean> = _loadingFoodData

    private val _doctorData = MutableLiveData<DataResponse<GetUserResponse>>()
    val doctorData: LiveData<DataResponse<GetUserResponse>> = _doctorData

    private val _loadingDoctorData = MutableLiveData<Boolean>()
    val loadingDoctorData: LiveData<Boolean> = _loadingDoctorData

    fun getDoctorData(){
        _loadingDoctorData.value = true
        ApiConfig.getApiService().getUser(firebaseAuth.currentUser?.uid!!).enqueue(object : Callback<GetUserResponse>{
            override fun onFailure(call: retrofit2.Call<GetUserResponse>, t: Throwable) {
                _loadingDoctorData.value = false
                _doctorData.value = DataResponse.Failed(t.message.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetUserResponse>, response: retrofit2.Response<GetUserResponse>) {
                _loadingDoctorData.value = false
                if(response.isSuccessful){
                    response.body()?.let {
                        _doctorData.value = DataResponse.Success(it)
                    }
                }else{
                    _doctorData.value = DataResponse.Failed(response.message())
                }
            }
        })
    }

    fun getFoodData(){
        _loadingFoodData.value = true
        ApiConfig.getApiService().getUser(firebaseAuth.currentUser?.uid!!).enqueue(object : Callback<GetUserResponse>{
            override fun onFailure(call: retrofit2.Call<GetUserResponse>, t: Throwable) {
                _loadingFoodData.value = false
                _foodData.value = DataResponse.Failed(t.message.toString())
            }

            override fun onResponse(call: retrofit2.Call<GetUserResponse>, response: retrofit2.Response<GetUserResponse>) {
                _loadingFoodData.value = false
                if(response.isSuccessful){
                    response.body()?.let {
                        _foodData.value = DataResponse.Success(it)
                    }
                }else{
                    _foodData.value = DataResponse.Failed(response.message())
                }
            }
        })
    }


    fun getPatientData() {
        _loadingPatientData.value = true
        firebaseAuth.currentUser?.uid?.let { uid ->
            ApiConfig.getApiService().getUser(uid).enqueue(object : Callback<GetUserResponse> {
                override fun onFailure(call: retrofit2.Call<GetUserResponse>, t: Throwable) {
                    _loadingPatientData.value = false
                    t.message?.let { _patientData.value = DataResponse.Failed(it) } ?: run {
                        _patientData.value = DataResponse.Failed("No data found")
                    }
                    _loadingPatientData.value = false
                }

                override fun onResponse(
                    call: retrofit2.Call<GetUserResponse>,
                    response: retrofit2.Response<GetUserResponse>
                ) {
                    _loadingPatientData.value = false
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _patientData.value = DataResponse.Success(it)
                        } ?: run {
                            _patientData.value = DataResponse.Failed("No data found")
                        }
                    } else {
                        _patientData.value = DataResponse.Failed(response.message())
                    }
                    _loadingPatientData.value = false
                }
            })
        }
    }
}