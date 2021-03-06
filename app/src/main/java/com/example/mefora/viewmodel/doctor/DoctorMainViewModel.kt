package com.example.mefora.viewmodel.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mefora.api.ApiConfig
import com.example.mefora.api.model.*
import com.example.mefora.util.DataResponse
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header

class DoctorMainViewModel : ViewModel() {

    fun getAuth() = FirebaseAuth.getInstance()

    private val _patientListData = MutableLiveData<DataResponse<GetPatientListResponse>>()
    val patientListData: LiveData<DataResponse<GetPatientListResponse>> = _patientListData

    private val _loadingPatientListData = MutableLiveData<Boolean>()
    val loadingPatientListData: LiveData<Boolean> = _loadingPatientListData

    private val _createPatientListData = MutableLiveData<DataResponse<CreatePatientListResponse>>()
    val createPatientListData: LiveData<DataResponse<CreatePatientListResponse>> =
        _createPatientListData

    private val _loadingCreatePatientListData = MutableLiveData<Boolean>()
    val loadingCreatePatientListData: LiveData<Boolean> = _loadingCreatePatientListData

    private val _doctorData = MutableLiveData<DataResponse<GetUserResponse>>()
    val doctorData: LiveData<DataResponse<GetUserResponse>> = _doctorData

    private val _loadingDoctorData = MutableLiveData<Boolean>()
    val loadingDoctorData: LiveData<Boolean> = _loadingDoctorData

    private val _patientData = MutableLiveData<DataResponse<GetUserResponse>>()
    val patientData: LiveData<DataResponse<GetUserResponse>> = _patientData

    private val _loadingPatientData = MutableLiveData<Boolean>()
    val loadingPatientData: LiveData<Boolean> = _loadingPatientData

    private val _patientDiseaseData = MutableLiveData<DataResponse<GetDiseaseResponse>>()
    val patientDiseaseData: LiveData<DataResponse<GetDiseaseResponse>> = _patientDiseaseData

    private val _loadingPatientDiseaseData = MutableLiveData<Boolean>()
    val loadingPatientDiseaseData: LiveData<Boolean> = _loadingPatientDiseaseData

    private val _deletePatientData = MutableLiveData<DataResponse<DeleteUserResponse>>()
    val deletePatientData: LiveData<DataResponse<DeleteUserResponse>> = _deletePatientData

    private val _loadingDeletePatientData = MutableLiveData<Boolean>()
    val loadingDeletePatientData: LiveData<Boolean> = _loadingDeletePatientData

    private val _diseaseData = MutableLiveData<DataResponse<GetDiseaseResponse>>()
    val diseaseData: LiveData<DataResponse<GetDiseaseResponse>> = _diseaseData

    private val _loadingDiseaseData = MutableLiveData<Boolean>()
    val loadingDiseaseData: LiveData<Boolean> = _loadingDiseaseData

    private val _addPatientDiseaseData = MutableLiveData<DataResponse<AddDiseaseResponse>>()
    val addPatientDiseaseData: LiveData<DataResponse<AddDiseaseResponse>> =
        _addPatientDiseaseData

    private val _loadingAddPatientDiseaseData = MutableLiveData<Boolean>()
    val loadingAddPatientDiseaseData: LiveData<Boolean> = _loadingAddPatientDiseaseData

    private val _editProfile = MutableLiveData<DataResponse<UpdateUserResponse>>()
    val editProfile: LiveData<DataResponse<UpdateUserResponse>> = _editProfile

    private val _loadingEditProfile = MutableLiveData<Boolean>()
    val loadingEditProfile: LiveData<Boolean> = _loadingEditProfile

    private val _logout = MutableLiveData<DataResponse<Boolean>>()
    val logout: LiveData<DataResponse<Boolean>> = _logout

    private val _loadingLogout = MutableLiveData<Boolean>()
    val loadingLogout: LiveData<Boolean> = _loadingLogout

    fun editUserData(userData: GetUserResponse) {
        _loadingEditProfile.value = true
        val authData = getAuth().currentUser
        ApiConfig.getApiService().updateUser(
            authData?.uid.toString(),
            authData?.uid.toString(),
            userData.name.toString(),
            userData.nickname.toString(),
            userData.address.toString(),
            userData.address.toString(),
            false,
        ).enqueue(object : Callback<UpdateUserResponse> {
            override fun onResponse(
                call: Call<UpdateUserResponse>,
                response: Response<UpdateUserResponse>
            ) {
                if (response.isSuccessful) {
                    _editProfile.value = DataResponse.Success(response.body()!!)
                } else {
                    _editProfile.value = DataResponse.Failed(response.message())
                }
                _loadingEditProfile.value = false
            }

            override fun onFailure(call: Call<UpdateUserResponse>, t: Throwable) {
                _editProfile.value = DataResponse.Failed(t.message.toString())
                _loadingEditProfile.value = false
            }
        })
    }


    fun addPatientDisease(diseaseId: String, diseaseName: String, patientId: String) {
        _loadingAddPatientDiseaseData.value = true
        ApiConfig.getApiService().createUserDisease(
            patientId,
            diseaseId,
            diseaseName
        ).enqueue(object : Callback<AddDiseaseResponse> {
            override fun onResponse(
                call: Call<AddDiseaseResponse>,
                response: Response<AddDiseaseResponse>
            ) {
                _loadingAddPatientDiseaseData.value = false
                if (response.isSuccessful) {
                    _addPatientDiseaseData.value = DataResponse.Success(response.body()!!)
                } else {
                    _addPatientDiseaseData.value = DataResponse.Failed(response.message())
                }
            }

            override fun onFailure(call: Call<AddDiseaseResponse>, t: Throwable) {
                _loadingAddPatientDiseaseData.value = false
                _addPatientDiseaseData.value = DataResponse.Failed(t.message.toString())
            }
        })
    }


    fun getDiseaseData() {
        _loadingDiseaseData.value = true
        ApiConfig.getApiService().getAllDisease().enqueue(object : Callback<GetDiseaseResponse> {
            override fun onFailure(call: Call<GetDiseaseResponse>, t: Throwable) {
                _loadingDiseaseData.value = false
                _diseaseData.value = DataResponse.Failed(t.message.toString())
            }

            override fun onResponse(
                call: Call<GetDiseaseResponse>,
                response: Response<GetDiseaseResponse>
            ) {
                _loadingDiseaseData.value = false
                if (response.isSuccessful) {
                    _diseaseData.value = DataResponse.Success(response.body()!!)
                } else {
                    _diseaseData.value = DataResponse.Failed(response.message())
                }
            }
        })
    }


    fun deletePatientData(patientUID: String) {
        _loadingDeletePatientData.value = true
        ApiConfig.getApiService().deleteUser(patientUID)
            .enqueue(object : Callback<DeleteUserResponse> {
                override fun onFailure(call: Call<DeleteUserResponse>, t: Throwable) {
                    _loadingDeletePatientData.value = false
                    _deletePatientData.value = DataResponse.Failed(t.message.toString())
                }

                override fun onResponse(
                    call: Call<DeleteUserResponse>,
                    response: Response<DeleteUserResponse>
                ) {
                    _loadingDeletePatientData.value = false
                    _deletePatientData.value = DataResponse.Success(response.body()!!)
                }
            })
    }

    fun getPatientDiseaseData(patientUID: String) {
        _loadingPatientDiseaseData.value = true
        ApiConfig.getApiService().getUserDisease(patientUID)
            .enqueue(object : Callback<GetDiseaseResponse> {
                override fun onResponse(
                    call: Call<GetDiseaseResponse>,
                    response: Response<GetDiseaseResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _patientDiseaseData.value = DataResponse.Success(it)
                        }
                    } else {
                        _patientDiseaseData.value = DataResponse.Failed(response.message())
                    }
                    _loadingPatientDiseaseData.value = false
                }

                override fun onFailure(call: Call<GetDiseaseResponse>, t: Throwable) {
                    _patientDiseaseData.value = DataResponse.Failed(t.message.toString())
                    _loadingPatientDiseaseData.value = false
                }
            })
    }

    fun getPatientData(patientUID: String) {
        _loadingPatientData.value = true
        ApiConfig.getApiService().getUser(patientUID).enqueue(object : Callback<GetUserResponse> {
            override fun onFailure(call: Call<GetUserResponse>, t: Throwable) {
                _loadingPatientData.value = false
                _patientData.value = DataResponse.Failed(t.message.toString())
            }

            override fun onResponse(
                call: Call<GetUserResponse>,
                response: Response<GetUserResponse>
            ) {
                _loadingPatientData.value = false
                _patientData.value = DataResponse.Success(response.body()!!)
            }
        })
    }

    fun getPatientList() {
        _loadingPatientListData.value = true
        getAuth().currentUser?.uid?.let {
            ApiConfig.getApiService().getPatientList(it)
                .enqueue(object : Callback<GetPatientListResponse> {
                    override fun onResponse(
                        call: Call<GetPatientListResponse>,
                        response: Response<GetPatientListResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { data ->
                                _patientListData.value = DataResponse.Success(data)
                            } ?: run {
                                _patientListData.value = DataResponse.Failed(response.message())
                            }
                        } else {
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

    fun getDoctorData() {
        _loadingDoctorData.value = true
        getAuth().currentUser?.uid?.let {
            ApiConfig.getApiService().getUser(it).enqueue(object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { data ->
                            _doctorData.value = DataResponse.Success(data)
                        } ?: run {
                            _doctorData.value = DataResponse.Failed(response.message())
                        }
                    } else {
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

    fun addPatientToList(patientUID: String) {
        _loadingPatientListData.value = true
        getAuth().currentUser?.uid?.let {
            ApiConfig.getApiService().createList(it, it, patientUID)
                .enqueue(object : Callback<CreatePatientListResponse> {
                    override fun onResponse(
                        call: Call<CreatePatientListResponse>,
                        response: Response<CreatePatientListResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { data ->
                                _createPatientListData.value = DataResponse.Success(data)
                            } ?: run {
                                _createPatientListData.value =
                                    DataResponse.Failed(response.message())
                            }
                        } else {
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

    fun logout() {
        getAuth().signOut()
        if (getAuth().currentUser == null) {
            _logout.value = DataResponse.Success(true)
        } else {
            _logout.value = DataResponse.Failed("Logout failed")
        }
    }
}