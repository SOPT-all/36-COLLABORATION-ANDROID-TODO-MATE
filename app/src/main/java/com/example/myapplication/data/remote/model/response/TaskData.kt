package com.example.myapplication.data.remote.model.response

data class MainTaskData(
    var mainTaskId: Long? = null,
    var taskContent: String? = null,
    var startAt: String? = null,
    var endAt: String? = null,
    var routineType: String? = null,
    var priority: Int? = null,
    var category: String? = null,
    var taskDate: String? = null,
    var completed: Boolean? = null,
    var subTasks: MutableList<SubTaskData>? = null
)

data class SubTaskData(
    var subTaskId: Long? = null,
    var content: String? = null,
    var completed: Boolean? = null
)