package com.example.myapplication.data.remote.model.response

data class AddMainTaskResponse(
    var mainTaskId: Long? = null,
    var taskContent: String? = null,
    var importance: String? = null,
    var category: String? = null,
    var taskDate: String? = null,
    var completed: Boolean? = null
)