package com.example.mefora.api

import com.example.mefora.api.model.*
import com.example.mefora.util.DataResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/users")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun createUser(
        @Body userData: CreateUser
    ): Call<CreateUserResponse>

    @GET("api/users")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getAllUsers(
        @Header("Authorization") auth: String
    ): Call<GetAllUserResponse>

    @GET("api/users/uid")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getUser(
        @Header("token_uid") auth: String,
    ): Call<GetUserResponse>

    @PUT("api/users/uid")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updateUser(
        @Header("token_uid") auth: String,
        @Body UID: String,
        @Body name: String,
        @Body nickname: String,
        @Body address: String,
        @Body birth: String,
        @Body status: Boolean,
    ): Call<UpdateUserResponse>

    @DELETE("api/users/{UID}")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun deleteUser(
        @Header("Authorization") auth: String,
    ): Call<DeleteUserResponse>


    @POST("api/lists")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun createList(
        @Header("Authorization") auth: String,
        @Body patient_uid: String,
        @Body doctor_uid: String
    ): Call<CreatePatientListResponse>

    @GET("api/lists/doctor_uid")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getPatientList(
        @Header("doctor_uid") auth: String,
    ): Call<GetPatientListResponse>

    @POST("https://us-central1-aiplatform.googleapis.com/v1/projects/graceful-ratio-351309/locations/us-central1/endpoints/6984687197865639936:predict")
    @Headers(
        "Content-Type: application/json"
    )
    fun getPrediction(
        @Body instances: Instances
    ): Call<GetPredictionResponse>

    @POST("api/userDiseases")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun createUserDisease(
        @Body patient_uid: String,
        @Body disease_id: String,
        @Body disease_name: String
    ): Call<AddDiseaseResponse>

    @GET("api/userDiseases")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getUserDisease(
        @Header("patient_uid") auth: String,
    ): Call<GetDiseaseResponse>

    @GET("api/diseases")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun getAllDisease(
    ): Call<GetDiseaseResponse>

}