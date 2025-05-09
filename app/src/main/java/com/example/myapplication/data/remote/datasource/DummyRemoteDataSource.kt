package com.example.myapplication.data.remote.datasource

import com.example.myapplication.data.remote.model.response.BaseResponse
import com.example.myapplication.data.remote.model.response.DummyResponseDto


interface DummyRemoteDataSource {
    suspend fun funName(userId:Long): BaseResponse<DummyResponseDto>
}
