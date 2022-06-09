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
