package com.example.myapplication.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddSubTaskRequest(
    var content: String
)
