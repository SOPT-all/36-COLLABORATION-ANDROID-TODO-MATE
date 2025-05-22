package com.example.myapplication.domain.repository

import com.example.myapplication.data.remote.model.request.AddMainTaskRequest
import com.example.myapplication.data.remote.model.request.AddSubTaskRequest
import com.example.myapplication.data.remote.model.request.MainTaskCompleteRequest
import com.example.myapplication.data.remote.model.request.SubTaskCompleteRequest
import com.example.myapplication.data.remote.model.response.AddMainTaskResponse
import com.example.myapplication.data.remote.model.response.AddSubTaskResponse
import com.example.myapplication.data.remote.model.response.BaseResponse
import com.example.myapplication.data.remote.model.response.MainTaskCompleteResponse
import com.example.myapplication.data.remote.model.response.MainTaskData
import com.example.myapplication.data.remote.model.response.SubTaskCompleteResponse
import retrofit2.Response

interface TaskRepository {
    suspend fun getDetailTask(userId: Long, date: String): Response<BaseResponse<List<MainTaskData>>>
    suspend fun addMainTask(userId: Long, addMainTaskRequest: AddMainTaskRequest): Response<BaseResponse<AddMainTaskResponse>>
    suspend fun addSubTask(userId: Long, taskId: Long, addSubTaskRequest: AddSubTaskRequest): Response<BaseResponse<AddSubTaskResponse>>
    suspend fun mainTaskComplete(userId: Long, taskId: Long, mainTaskCompleteRequest: MainTaskCompleteRequest): Response<MainTaskCompleteResponse>
    suspend fun subTaskComplete(userId: Long, taskId: Long, subTaskCompleteRequest: SubTaskCompleteRequest): Response<SubTaskCompleteResponse>
}