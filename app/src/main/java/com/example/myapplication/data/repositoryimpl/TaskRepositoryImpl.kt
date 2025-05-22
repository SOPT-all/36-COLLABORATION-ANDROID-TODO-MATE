package com.example.myapplication.data.repositoryimpl

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
import com.example.myapplication.data.service.TaskService
import com.example.myapplication.domain.repository.TaskRepository
import retrofit2.Response

class TaskRepositoryImpl(
    private val taskService: TaskService
) : TaskRepository {
    override suspend fun getDetailTask(userId: Long, date: String): Response<BaseResponse<List<MainTaskData>>> =
        taskService.getDetailTask(userId, date)

    override suspend fun addMainTask(userId: Long, addMainTaskRequest: AddMainTaskRequest): Response<BaseResponse<AddMainTaskResponse>> =
        taskService.addMainTask(userId, addMainTaskRequest)

    override suspend fun addSubTask(userId: Long, taskId: Long, addSubTaskRequest: AddSubTaskRequest): Response<BaseResponse<AddSubTaskResponse>> =
        taskService.addSubTask(userId, taskId, addSubTaskRequest)

    override suspend fun mainTaskComplete(userId: Long, taskId: Long, mainTaskCompleteRequest: MainTaskCompleteRequest): Response<MainTaskCompleteResponse> =
        taskService.mainTaskComplete(userId, taskId, mainTaskCompleteRequest)

    override suspend fun subTaskComplete(userId: Long, taskId: Long, subTaskCompleteRequest: SubTaskCompleteRequest): Response<SubTaskCompleteResponse> =
        taskService.subTaskComplete(userId, taskId, subTaskCompleteRequest)

}