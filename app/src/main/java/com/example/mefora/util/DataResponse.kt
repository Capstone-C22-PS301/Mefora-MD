package com.example.mefora.util

sealed class DataResponse<T>(val msg: String? = null, val data: T? = null) {
    class Success<T>(data: T) : DataResponse<T>(data = data)
    class Failed<T>(msg: String) : DataResponse<T>(msg = msg)
}