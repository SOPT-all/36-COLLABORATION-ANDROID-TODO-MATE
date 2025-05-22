package com.example.myapplication.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class AddMainTaskRequest(
    var taskContent: String,
    var category: String,
    var taskDate: String
)