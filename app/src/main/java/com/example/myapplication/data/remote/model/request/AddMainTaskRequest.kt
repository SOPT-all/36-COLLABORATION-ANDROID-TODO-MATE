package com.example.myapplication.data.remote.model.request

data class AddMainTaskRequest(
    var taskContent: String,
    var category: String,
    var taskDate: String
)