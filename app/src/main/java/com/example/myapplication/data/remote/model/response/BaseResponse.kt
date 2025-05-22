package com.example.myapplication.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val code: String,
    val message: String,
    val data: T
)
