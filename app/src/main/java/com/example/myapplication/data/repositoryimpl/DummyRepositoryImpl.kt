package com.example.myapplication.data.repositoryimpl

import com.example.myapplication.data.local.datasource.DummyLocalDataSource
import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.datasource.DummyRemoteDataSource
import com.example.myapplication.domain.model.DummyData
import com.example.myapplication.domain.repository.DummyRepository

class DummyRepositoryImpl(
    private val remoteDataSource: DummyRemoteDataSource,
    private val localDataSource: DummyLocalDataSource

) : DummyRepository {

    override suspend fun funName(userId: Long): Result<DummyData> =
        runCatching {
            remoteDataSource.funName(userId = userId).data.toDomain()
        }

    override suspend fun setLocalUserNickname(nickname: String) {
        localDataSource.nickname = nickname
    }

    override suspend fun getLocalUserNickname(): String {
        return localDataSource.nickname
    }
}

