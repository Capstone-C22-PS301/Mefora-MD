package com.example.mefora.viewmodel.doctor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.api.model.CreatePatientListResponse
import com.example.mefora.api.model.GetPatientListResponse
import com.example.mefora.api.model.GetUserResponse
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoctorMainViewModel : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _patientListData = MutableLiveData<DataResponse<GetPatientListResponse>>()
    val patientListData: MutableLiveData<DataResponse<GetPatientListResponse>> = _patientListData

    private val _loadingPatientListData = MutableLiveData<Boolean>()
    val loadingPatientListData: MutableLiveData<Boolean> = _loadingPatientListData

    private val _createPatientListData = MutableLiveData<DataResponse<CreatePatientListResponse>>()
    val createPatientListData: MutableLiveData<DataResponse<CreatePatientListResponse>> = _createPatientListData

    private val _loadingCreatePatientListData = MutableLiveData<Boolean>()
    val loadingCreatePatientListData: MutableLiveData<Boolean> = _loadingCreatePatientListData

    private val _doctorData = MutableLiveData<DataResponse<GetUserResponse>>()
    val doctorData: MutableLiveData<DataResponse<GetUserResponse>> = _doctorData

    private val _loadingDoctorData = MutableLiveData<Boolean>()
    val loadingDoctorData: MutableLiveData<Boolean> = _loadingDoctorData

    fun getPatientList(){
        _loadingPatientListData.value = true
        firebaseAuth.currentUser?.uid?.let {
            ApiConfig.getApiService().getPatientList(it).enqueue(object : Callback<GetPatientListResponse>{
                override fun onResponse(
                    call: Call<GetPatientListResponse>,
                    response: Response<GetPatientListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { data ->
                            _patientListData.value = DataResponse.Success(data)
                        }?: run {
                            _patientListData.value = DataResponse.Failed(response.message())
                        }
                    }else{
                        _patientListData.value = DataResponse.Failed(response.message())
                    }
                    _loadingPatientListData.value = false
                }

                override fun onFailure(call: Call<GetPatientListResponse>, t: Throwable) {
                    t.message?.let { msg ->
                        _patientListData.value = DataResponse.Failed(msg)
                    }
                    _loadingPatientListData.value = false
                }
            })

        }
    }
    
    fun getDoctorData(){
        _loadingDoctorData.value = true
        firebaseAuth.currentUser?.uid?.let {
            ApiConfig.getApiService().getUser(it).enqueue(object : Callback<GetUserResponse>{
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { data ->
                            _doctorData.value = DataResponse.Success(data)
                        }?: run {
                            _doctorData.value = DataResponse.Failed(response.message())
                        }
                    }else{
                        _doctorData.value = DataResponse.Failed(response.message())
                    }
                    _loadingDoctorData.value = false
                }

                override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                    t.message?.let { msg ->
                        _doctorData.value = DataResponse.Failed(msg)
                    }
                    _loadingDoctorData.value = false
                }
            })

        }
    }

    fun addPatientToList(patientUID: String){
        _loadingPatientListData.value = true
        firebaseAuth.currentUser?.uid?.let {
            ApiConfig.getApiService().createList(it, it, patientUID).enqueue(object : Callback<CreatePatientListResponse>{
                override fun onResponse(
                    call: Call<CreatePatientListResponse>,
                    response: Response<CreatePatientListResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.let { data ->
                            _createPatientListData.value = DataResponse.Success(data)
                        }?: run {
                            _createPatientListData.value = DataResponse.Failed(response.message())
                        }
                    }else{
                        _createPatientListData.value = DataResponse.Failed(response.message())
                    }
                    _loadingCreatePatientListData.value = false
                }

                override fun onFailure(call: Call<CreatePatientListResponse>, t: Throwable) {
                    t.message?.let { msg ->
                        _createPatientListData.value = DataResponse.Failed(msg)
                    }
                    _loadingCreatePatientListData.value = false
                }
            })

        }
    }
}