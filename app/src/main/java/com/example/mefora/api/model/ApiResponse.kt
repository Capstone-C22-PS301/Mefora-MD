package com.example.mefora.api.model

import com.google.gson.annotations.SerializedName

data class CreateUserResponse(
    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class GetAllUserResponse(

    @field:SerializedName("GetAllUser")
    val getAllUser: List<GetAllUserResponseItem?>? = null
)

data class GetAllUserResponseItem(

    @field:SerializedName("UID")
    val uID: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nickname")
    val nickname: String? = null,

    @field:SerializedName("birth")
    val birth: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class GetUserResponse(

    @field:SerializedName("UID")
    val uID: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("nickname")
    val nickname: String? = null,

    @field:SerializedName("birth")
    val birth: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class UpdateUserResponse(
    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DeleteUserResponse(
    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class CreatePatientListResponse(
    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class GetPatientListResponse(

    @field:SerializedName("GetPatientListResponse")
    val getPatientListResponse: List<GetPatientListResponseItem?>? = null
)

data class GetPatientListResponseItem(

    @field:SerializedName("doctor_uid")
    val doctorUid: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("patient_uid")
    val patientUid: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

