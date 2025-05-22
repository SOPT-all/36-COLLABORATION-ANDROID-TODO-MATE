package com.example.myapplication.data.service

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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskService {
    @GET("main-tasks/detail")
    suspend fun getDetailTask(
        @Header("userId") userId: Long,
        @Query("date") date: String
    ): Response<BaseResponse<List<MainTaskData>>>

    @POST("main-tasks")
    suspend fun addMainTask(
        @Header("userId") userId: Long,
        @Body addMainTaskRequest: AddMainTaskRequest
    ): Response<BaseResponse<AddMainTaskResponse>>

    @POST("sub-tasks")
    suspend fun addSubTask(
        @Header("userId") userId: Long,
        @Header("taskId") taskId: Long,
        @Body addSubTaskRequest: AddSubTaskRequest
    ): Response<BaseResponse<AddSubTaskResponse>>

    @PATCH("main-tasks")
    suspend fun mainTaskComplete(
        @Header("userId") userId: Long,
        @Header("taskId") taskId: Long,
        @Body mainTaskCompleteRequest: MainTaskCompleteRequest
    ): Response<MainTaskCompleteResponse>

    @PATCH("sub-tasks")
    suspend fun subTaskComplete(
        @Header("userId") userId: Long,
        @Header("taskId") taskId: Long,
        @Body subTaskCompleteRequest: SubTaskCompleteRequest
    ): Response<SubTaskCompleteResponse>

}