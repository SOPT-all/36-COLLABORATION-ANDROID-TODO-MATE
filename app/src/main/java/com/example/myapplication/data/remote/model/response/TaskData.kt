package com.example.myapplication.data.remote.model.response

import kotlinx.serialization.Serializable

@Serializable
data class MainTaskData(
    var mainTaskId: Long? = null,
    var taskContent: String? = null,
    var category: String? = null,
    var importance: String? = null,
    var completed: Boolean? = null,
    var subTasks: MutableList<SubTaskData>? = null
)

@Serializable
data class SubTaskData(
    var subTaskId: Long? = null,
    var mainTaskId: Long? = null,
    var content: String? = null,
    var completed: Boolean? = null
)