package com.example.myapplication.presentation.home

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


    private val _focusOnTask = MutableSharedFlow<Triple<Int, Int, Int>>()
    val focusOnTask: MutableSharedFlow<Triple<Int, Int, Int>> get() = _focusOnTask

    // cate1
    private val cate1TaskList : MutableList<MainTaskData> = mutableListOf()

    private val _addCate1SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate1SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate1SubTaskFlow

    private val _deleteCate1SubTask = MutableSharedFlow<Int?>()
    val deleteCate1SubTask: MutableSharedFlow<Int?> get() = _deleteCate1SubTask

    private val _cate1TaskBlank = MutableStateFlow(true)
    val cate1TaskBlank: MutableStateFlow<Boolean> get() = _cate1TaskBlank

    private val _cate1MainTaskChecked = MutableSharedFlow<Pair<Int, Boolean>?>()
    val cate1MainTaskChecked: MutableSharedFlow<Pair<Int, Boolean>?> get() = _cate1MainTaskChecked

    private val _cate1SubTaskChecked = MutableSharedFlow<Triple<Int, Int, Boolean>?>()
    val cate1SubTaskChecked: MutableSharedFlow<Triple<Int, Int, Boolean>?> get() = _cate1SubTaskChecked


    // cate2
    private val cate2TaskList : MutableList<MainTaskData> = mutableListOf()

    private val _addCate2SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate2SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate2SubTaskFlow

    private val _deleteCate2SubTask = MutableSharedFlow<Int?>()
    val deleteCate2SubTask: MutableSharedFlow<Int?> get() = _deleteCate2SubTask

    private val _cate2TaskBlank = MutableStateFlow(true)
    val cate2TaskBlank: MutableStateFlow<Boolean> get() = _cate2TaskBlank

    private val _cate2MainTaskChecked = MutableSharedFlow<Pair<Int, Boolean>?>()
    val cate2MainTaskChecked: MutableSharedFlow<Pair<Int, Boolean>?> get() = _cate2MainTaskChecked

    private val _cate2SubTaskChecked = MutableSharedFlow<Triple<Int, Int, Boolean>?>()
    val cate2SubTaskChecked: MutableSharedFlow<Triple<Int, Int, Boolean>?> get() = _cate2SubTaskChecked


    // cate3
    private val cate3TaskList : MutableList<MainTaskData> = mutableListOf()

    private val _addCate3SubTaskFlow = MutableSharedFlow<Int?>()
    val addCate3SubTaskFlow: MutableSharedFlow<Int?> get() = _addCate3SubTaskFlow

    private val _deleteCate3SubTask = MutableSharedFlow<Int?>()
    val deleteCate3SubTask: MutableSharedFlow<Int?> get() = _deleteCate3SubTask

    private val _cate3TaskBlank = MutableStateFlow(true)
    val cate3TaskBlank: MutableStateFlow<Boolean> get() = _cate3TaskBlank

    private val _cate3MainTaskChecked = MutableSharedFlow<Pair<Int, Boolean>?>()
    val cate3MainTaskChecked: MutableSharedFlow<Pair<Int, Boolean>?> get() = _cate3MainTaskChecked

    private val _cate3SubTaskChecked = MutableSharedFlow<Triple<Int, Int, Boolean>?>()
    val cate3SubTaskChecked: MutableSharedFlow<Triple<Int, Int, Boolean>?> get() = _cate3SubTaskChecked



    fun getCategoryTaskList() = viewModelScope.launch {
        //TODO 서버연결 후 테스크 내용들 받고 분류
    }

    fun addMainTask(mainTaskContent: String, categoryIdx: Int) = viewModelScope.launch {
        val newMainTask = MainTaskData(mainTaskContent, false, mutableListOf())

        when(categoryIdx) {
            0 -> cate1TaskList.add(newMainTask)
            1 -> cate2TaskList.add(newMainTask)
            else -> cate3TaskList.add(newMainTask)
        }
    }

    fun addSubTaskLayout(mainTaskIdx: Int, categoryIdx: Int) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> _addCate1SubTaskFlow.emit(mainTaskIdx)
            1 -> _addCate2SubTaskFlow.emit(mainTaskIdx)
            else -> _addCate3SubTaskFlow.emit(mainTaskIdx)
        }
    }

    fun addSubTask(categoryIdx: Int, mainTaskIdx: Int, subTaskContent: String) = viewModelScope.launch {
        val originalListValue = when(categoryIdx) {
            0 -> cate1TaskList
            1 -> cate2TaskList
            else -> cate3TaskList
        }

        val newSubTask = SubTaskData(subTaskContent, false)
        val newSubTaskList = originalListValue[mainTaskIdx].subTaskList
        newSubTaskList.add(newSubTask)

        when(categoryIdx) {
            0 -> cate1TaskList[mainTaskIdx].subTaskList.add(newSubTask)
            1 -> cate2TaskList[mainTaskIdx].subTaskList.add(newSubTask)
            else -> cate3TaskList[mainTaskIdx].subTaskList.add(newSubTask)
        }

        //TODO 서버연결 후 서브테스크 내용 보내기
    }

    fun checkMainTask(categoryIdx: Int, mainTaskIdx: Int, checked: Boolean) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> {
                cate1TaskList[mainTaskIdx].checked = checked
                cate1TaskList[mainTaskIdx].subTaskList.forEach {
                    it.checked = checked
                }

                _cate1MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate1TaskList[mainTaskIdx].subTaskList.forEachIndexed { index, _ ->
                    _cate1SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
            1 -> {
                cate2TaskList[mainTaskIdx].checked = checked
                cate2TaskList[mainTaskIdx].subTaskList.forEach {
                    it.checked = checked
                }

                _cate2MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate2TaskList[mainTaskIdx].subTaskList.forEachIndexed { index, _ ->
                    _cate2SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
            else -> {
                cate3TaskList[mainTaskIdx].checked = checked
                cate3TaskList[mainTaskIdx].subTaskList.forEach {
                    it.checked = checked
                }

                _cate3MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate3TaskList[mainTaskIdx].subTaskList.forEachIndexed { index, _ ->
                    _cate3SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
        }
    }

    fun checkSubTask(categoryIdx: Int, mainTaskIdx: Int, subTaskIdx: Int, checked: Boolean) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> {
                cate1TaskList[mainTaskIdx].subTaskList[subTaskIdx].checked = checked
                _cate1SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

                val isAllItemTrue = cate1TaskList[mainTaskIdx].subTaskList.all { it.checked == true }
                val isAllItemFalse = cate1TaskList[mainTaskIdx].subTaskList.all { it.checked == false }

                if(isAllItemTrue || isAllItemFalse) {
                    cate1TaskList[mainTaskIdx].checked = isAllItemTrue
                    _cate1MainTaskChecked.emit(Pair(mainTaskIdx, isAllItemTrue))
                }
            }
            1 -> {
                cate2TaskList[mainTaskIdx].subTaskList[subTaskIdx].checked = checked
                _cate2SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

                val isAllItemTrue = cate2TaskList[mainTaskIdx].subTaskList.all { it.checked == true }
                val isAllItemFalse = cate2TaskList[mainTaskIdx].subTaskList.all { it.checked == false }

                if(isAllItemTrue || isAllItemFalse) {
                    cate2TaskList[mainTaskIdx].checked = isAllItemTrue
                    _cate2MainTaskChecked.emit(Pair(mainTaskIdx, isAllItemTrue))
                }
            }
            else -> {
                cate3TaskList[mainTaskIdx].subTaskList[subTaskIdx].checked = checked
                _cate3SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

                val isAllItemTrue = cate3TaskList[mainTaskIdx].subTaskList.all { it.checked == true }
                val isAllItemFalse = cate3TaskList[mainTaskIdx].subTaskList.all { it.checked == false }

                if(isAllItemTrue || isAllItemFalse) {
                    cate3TaskList[mainTaskIdx].checked = isAllItemTrue
                    _cate3MainTaskChecked.emit(Pair(mainTaskIdx, isAllItemTrue))
                }
            }
        }
    }

    fun deleteSubItemLayout(categoryIdx: Int, mainTaskIdx: Int) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> {
                _deleteCate1SubTask.emit(mainTaskIdx)
            }
            1 -> {
                _deleteCate2SubTask.emit(mainTaskIdx)
            }
            else -> {
                _deleteCate3SubTask.emit(mainTaskIdx)
            }
        }
    }

    fun taskIsBlank(taskIsBlank: Boolean, categoryIdx: Int) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> _cate1TaskBlank.emit(taskIsBlank)
            1 -> _cate2TaskBlank.emit(taskIsBlank)
            else -> _cate3TaskBlank.emit(taskIsBlank)
        }
    }

    fun focusOnTask(categoryIdx: Int, mainTaskIdx: Int, subTaskIdx: Int = 0) = viewModelScope.launch {
        _focusOnTask.emit(Triple(categoryIdx, mainTaskIdx, subTaskIdx))
    }
}

data class MainTaskData(
    var title: String,
    var checked: Boolean,
    var subTaskList: MutableList<SubTaskData>
)

data class SubTaskData(
    var title: String,
    var checked: Boolean
)