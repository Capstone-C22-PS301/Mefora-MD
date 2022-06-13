package com.example.mefora.api.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uID)
        parcel.writeString(createdAt)
        parcel.writeString(address)
        parcel.writeString(name)
        parcel.writeString(nickname)
        parcel.writeString(birth)
        parcel.writeValue(status)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetUserResponse> {
        override fun createFromParcel(parcel: Parcel): GetUserResponse {
            return GetUserResponse(parcel)
        }

        override fun newArray(size: Int): Array<GetUserResponse?> {
            return arrayOfNulls(size)
        }
    }
}

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(doctorUid)
        parcel.writeString(createdAt)
        parcel.writeString(id)
        parcel.writeString(patientUid)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GetPatientListResponseItem> {
        override fun createFromParcel(parcel: Parcel): GetPatientListResponseItem {
            return GetPatientListResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<GetPatientListResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class GetPredictionResponse(

    @field:SerializedName("deployedModelId")
    val deployedModelId: String? = null,

    @field:SerializedName("model")
    val model: String? = null,

    @field:SerializedName("predictions")
    val predictions: List<PredictionsItem?>? = null,

    @field:SerializedName("modelDisplayName")
    val modelDisplayName: String? = null
)

data class PredictionsItem(

    @field:SerializedName("output_2")
    val output2: List<String?>? = null,

    @field:SerializedName("output_1")
    val output1: List<Double?>? = null
)

data class AddDiseaseResponse(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class GetDiseaseResponse(

    @field:SerializedName("GetDiseaseResponse")
    val getDiseaseResponse: List<GetDiseaseResponseItem?>? = null
)

data class GetDiseaseResponseItem(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("disease_id")
    val diseaseId: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("disease_name")
    val diseaseName: String? = null,

    @field:SerializedName("patient_uid")
    val patientUid: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class DeleteUserDisease(

    @field:SerializedName("success")
    val success: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)

