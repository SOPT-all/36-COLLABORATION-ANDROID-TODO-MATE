package com.example.myapplication.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.repository.DummyRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: DummyRepository
) : ViewModel() {
    private val _dummyData = MutableStateFlow("")
    val dummyData: MutableStateFlow<String> get() = _dummyData

    private val _localData = MutableStateFlow("")
    val localData: MutableStateFlow<String> get() = _localData


    // cate1
    private val _cate1TaskList = MutableStateFlow<MutableList<MutableList<String>>>(mutableListOf())
    val cate1TaskList: MutableStateFlow<MutableList<MutableList<String>>> get() = _cate1TaskList

    private val _addCate1SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate1SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate1SubTaskFlow

    private val _cate1TaskBlank = MutableStateFlow(true)
    val cate1TaskBlank: MutableStateFlow<Boolean> get() = _cate1TaskBlank

    // cate2
    private val _cate2TaskList = MutableStateFlow<MutableList<MutableList<String>>>(mutableListOf())
    val cate2TaskList: MutableStateFlow<MutableList<MutableList<String>>> get() = _cate2TaskList

    private val _addCate2SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate2SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate2SubTaskFlow

    private val _cate2TaskBlank = MutableStateFlow(true)
    val cate2TaskBlank: MutableStateFlow<Boolean> get() = _cate2TaskBlank

    // cate3
    private val _cate3TaskList = MutableStateFlow<MutableList<MutableList<String>>>(mutableListOf())
    val cate3TaskList: MutableStateFlow<MutableList<MutableList<String>>> get() = _cate3TaskList

    private val _addCate3SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate3SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate3SubTaskFlow

    private val _cate3TaskBlank = MutableStateFlow(true)
    val cate3TaskBlank: MutableStateFlow<Boolean> get() = _cate3TaskBlank




    fun addMainTask(mainTaskContent: String) = viewModelScope.launch {
        val newMainTask = mutableListOf(mainTaskContent)
        val newMainTaskList = _cate1TaskList.value
        newMainTaskList.add(newMainTask)

        _cate1TaskList.emit(newMainTaskList)
    }

    fun addSubTaskLayout(mainTaskIdx: Int) = viewModelScope.launch {
        _addCate1SubTaskFlow.emit(mainTaskIdx)
    }

    fun taskIsBlank(taskIsBlank: Boolean) = viewModelScope.launch {
        _cate1TaskBlank.emit(taskIsBlank)
    }


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

data class CategoryItem(
    var title: String,
    var checked: Boolean
)