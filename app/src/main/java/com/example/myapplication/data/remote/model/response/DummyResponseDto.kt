package com.example.myapplication.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DummyResponseDto(
    @SerialName("nickname")
    val nickname: String
)
