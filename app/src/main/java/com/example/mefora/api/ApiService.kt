package com.example.mefora.api

import com.example.mefora.api.model.*
import com.example.mefora.util.DataResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("api/users")
    fun createUser(
        @Header("Authorization") auth: String,
        @Body status: Boolean
    ): Call<DataResponse<CreateUserResponse>>

    @GET("api/users")
    fun getAllUsers(
        @Header("Authorization") auth: String
    ): Call<GetAllUserResponse>

    @GET("api/users/{UID}")
    fun getUser(
        @Header("Authorization") auth: String,
        @Path("UID") UID: String
    ): Call<GetUserResponse>

    @PUT("api/users/{UID}")
    fun updateUser(
        @Path("UID") UID: String,
        @Body status: Boolean
    ): Call<UpdateUserResponse>

    @DELETE("api/users/{UID}")
    fun deleteUser(
        @Header("Authorization") auth: String,
    ): Call<DeleteUserResponse>


    @POST("http://localhost:8080/api/lists")
    fun createList(
        @Header("Authorization") auth: String,
        @Body status: Boolean
    ): Call<DataResponse<CreatePatientListResponse>>

}