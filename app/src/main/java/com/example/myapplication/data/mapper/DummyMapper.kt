package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.model.response.DummyResponseDto
import com.example.myapplication.domain.model.DummyData

fun DummyResponseDto.toDomain(): DummyData {
    return DummyData(data = nickname+"입니다")
}

