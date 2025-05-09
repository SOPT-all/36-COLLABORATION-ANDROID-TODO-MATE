package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.DummyData

interface DummyRepository {
    suspend fun funName(userId: Long): Result<DummyData>
}
