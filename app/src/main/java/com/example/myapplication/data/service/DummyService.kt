package com.example.myapplication.data.service

import com.example.myapplication.data.remote.model.response.BaseResponse
import com.example.myapplication.data.remote.model.response.DummyResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface DummyService {
    @GET("api/v1/users/me")
    suspend fun funName(
        @Header("userId") userId: Long
    ): BaseResponse<DummyResponseDto>
}
