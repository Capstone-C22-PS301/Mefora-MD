package com.example.mefora.api.model

data class RequestModel(
    private val name: String?
)

data class PredictionModel(
    private val Instances: Instances?
)

data class Instances(
    val Disease: String?,
    val User_Id: String?
)

data class CreateUser(
    val UID: String?,
    val name: String?,
    val nickname: String?,
    val address: String?,
    val birth: String?,
    val status : Boolean?,
)
