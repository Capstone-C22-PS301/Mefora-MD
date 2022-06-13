package com.example.mefora.viewmodel.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.api.model.*
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Callback
import java.util.*

class PatientMainViewModel : ViewModel() {

    fun getAuth() = FirebaseAuth.getInstance()

    private val _patientData = MutableLiveData<DataResponse<GetUserResponse>>()
    val patientData: LiveData<DataResponse<GetUserResponse>> = _patientData

    private val _loadingPatientData = MutableLiveData<Boolean>()
    val loadingPatientData: LiveData<Boolean> = _loadingPatientData

    private val _foodData = MutableLiveData<DataResponse<GetPredictionResponse>>()
    val foodData: LiveData<DataResponse<GetPredictionResponse>> = _foodData

    private val _loadingFoodData = MutableLiveData<Boolean>()
    val loadingFoodData: LiveData<Boolean> = _loadingFoodData

    private val _doctorData = MutableLiveData<DataResponse<GetUserResponse>>()
    val doctorData: LiveData<DataResponse<GetUserResponse>> = _doctorData

    private val _loadingDoctorData = MutableLiveData<Boolean>()
    val loadingDoctorData: LiveData<Boolean> = _loadingDoctorData

    private val _diseaseData = MutableLiveData<DataResponse<GetDiseaseResponse>>()
    val diseaseData: LiveData<DataResponse<GetDiseaseResponse>> = _diseaseData

    private val _loadingDiseaseData = MutableLiveData<Boolean>()
    val loadingDiseaseData: LiveData<Boolean> = _loadingDiseaseData

    private val _editProfile = MutableLiveData<DataResponse<UpdateUserResponse>>()
    val editProfile: LiveData<DataResponse<UpdateUserResponse>> = _editProfile

    private val _loadingEditProfile = MutableLiveData<Boolean>()
    val loadingEditProfile: LiveData<Boolean> = _loadingEditProfile

    fun editProfile(patientData: GetUserResponse) {
        _loadingEditProfile.value = true
        ApiConfig.getApiService().updateUser(
            getAuth().currentUser?.uid ?: "",
            getAuth().currentUser?.uid ?: "",
            patientData.name ?: "",
            patientData.nickname ?: "",
            patientData.address ?: "",
            patientData.birth ?: "",
            false
        ).enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(
                call: retrofit2.Call<UpdateUserResponse>,
                response: retrofit2.Response<UpdateUserResponse>
            ) {
                _loadingEditProfile.value = false
                if (response.isSuccessful) {
                    response.body()?.let {
                        _editProfile.value = DataResponse.Success(it)
                    }
                } else {
                    _editProfile.value = DataResponse.Failed(response.message())
                }
            }

            override fun onFailure(call: retrofit2.Call<UpdateUserResponse>, t: Throwable) {
                _loadingEditProfile.value = false
                _editProfile.value = DataResponse.Failed(t.message.toString())
            }
        })
    }

    fun getDiseaseData() {
        _loadingDiseaseData.value = true
        ApiConfig.getApiService().getUserDisease(getAuth().currentUser?.uid.toString())
            .enqueue(object : Callback<GetDiseaseResponse> {
                override fun onFailure(call: retrofit2.Call<GetDiseaseResponse>, t: Throwable) {
                    _loadingDiseaseData.value = false
                    _diseaseData.value = DataResponse.Failed(t.message.toString())
                }

                override fun onResponse(
                    call: retrofit2.Call<GetDiseaseResponse>,
                    response: retrofit2.Response<GetDiseaseResponse>
                ) {
                    _loadingDiseaseData.value = false
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _diseaseData.value = DataResponse.Success(it)
                        }
                    }
                    _loadingDiseaseData.value = false
                }
            })
    }

    fun getDoctorData() {
        _loadingDoctorData.value = true
        ApiConfig.getApiService().getUser(getAuth().currentUser?.uid!!)
            .enqueue(object : Callback<GetUserResponse> {
                override fun onFailure(call: retrofit2.Call<GetUserResponse>, t: Throwable) {
                    _loadingDoctorData.value = false
                    _doctorData.value = DataResponse.Failed(t.message.toString())
                }

                override fun onResponse(
                    call: retrofit2.Call<GetUserResponse>,
                    response: retrofit2.Response<GetUserResponse>
                ) {
                    _loadingDoctorData.value = false
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _doctorData.value = DataResponse.Success(it)
                        }
                    } else {
                        _doctorData.value = DataResponse.Failed(response.message())
                    }
                }
            })
    }

    fun getFoodData(diseaseData: String) {
        _loadingFoodData.value = true
        val instances = Instances(diseaseData, "user_105")
        ApiConfig.getApiService().getPrediction(instances)
            .enqueue(object : Callback<GetPredictionResponse> {
                override fun onFailure(call: retrofit2.Call<GetPredictionResponse>, t: Throwable) {
                    _loadingFoodData.value = false
                    _foodData.value = DataResponse.Failed(t.message.toString())
                }

                override fun onResponse(
                    call: retrofit2.Call<GetPredictionResponse>,
                    response: retrofit2.Response<GetPredictionResponse>
                ) {
                    _loadingFoodData.value = false
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _foodData.value = DataResponse.Success(it)
                        }
                    } else {
                        _foodData.value = DataResponse.Failed(response.message())
                    }
                }
            })
    }


    fun getPatientData() {
        _loadingPatientData.value = true
        getAuth().currentUser?.uid?.let { uid ->
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

    fun getDate(): String {
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        return "$day/$month/$year"
    }


}