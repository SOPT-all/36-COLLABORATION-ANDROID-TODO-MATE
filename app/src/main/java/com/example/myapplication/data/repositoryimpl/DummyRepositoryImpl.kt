package com.example.myapplication.data.repositoryimpl

import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.datasource.DummyRemoteDataSource
import com.example.myapplication.domain.model.DummyData
import com.example.myapplication.domain.repository.DummyRepository

class DummyRepositoryImpl(
    private val remoteDataSource: DummyRemoteDataSource
) : DummyRepository {

    override suspend fun funName(userId: Long): Result<DummyData> =
        runCatching {
            remoteDataSource.funName(userId = userId).data.toDomain()
        }
}

