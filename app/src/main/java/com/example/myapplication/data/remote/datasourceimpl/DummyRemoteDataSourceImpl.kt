package com.example.myapplication.data.remote.datasourceimpl

import com.example.myapplication.data.remote.datasource.DummyRemoteDataSource
import com.example.myapplication.data.remote.model.response.BaseResponse
import com.example.myapplication.data.remote.model.response.DummyResponseDto
import com.example.myapplication.data.service.DummyService

class DummyRemoteDataSourceImpl(
    private val service: DummyService
) : DummyRemoteDataSource {

    override suspend fun funName(userId:Long): BaseResponse<DummyResponseDto> = service.funName(
        userId = userId
    )
}