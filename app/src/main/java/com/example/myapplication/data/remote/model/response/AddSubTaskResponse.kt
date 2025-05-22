package com.example.myapplication.data.remote.model.response

data class AddSubTaskResponse(
    var id: Long? = null,
    var content: String? = null,
    var completed: Boolean? = null,
    var mainTask: MainTaskData? = null
)