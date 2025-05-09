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

    private val _localData = MutableStateFlow("")
    val localData: MutableStateFlow<String> get() = _localData

    fun dummyRemoteFun() {
        viewModelScope.launch {
            repository.funName(userId = 1).onSuccess { result ->
                _dummyData.value = result.data
                dummyLocalFun(result.data)
            }
        }
    }

    private fun dummyLocalFun(nickname:String){
        viewModelScope.launch {
            if (repository.getLocalUserNickname().isEmpty()) {
                repository.setLocalUserNickname(nickname)
                _localData.value = "로컬에 저장된 닉네임이 없습니다."
            } else {
                _localData.value = "로컬에서 가져온 닉네임은 ${repository.getLocalUserNickname()}"
            }
        }
    }
}

