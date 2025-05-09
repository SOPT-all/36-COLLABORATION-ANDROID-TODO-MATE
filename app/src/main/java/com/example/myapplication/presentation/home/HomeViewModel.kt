package com.example.myapplication.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.DummyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DummyRepository
) : ViewModel() {
    private val _dummyData = MutableStateFlow("")
    val dummyData: MutableStateFlow<String> get() = _dummyData

    fun dummyFun() {
        viewModelScope.launch {
            repository.funName(userId = 1).onSuccess { result->
                _dummyData.value = result.data
            }
        }
    }
}

