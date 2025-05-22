package com.example.myapplication.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.model.response.MainTaskData
import com.example.myapplication.data.remote.model.response.SubTaskData
import com.example.myapplication.domain.repository.TaskRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val taskRepository: TaskRepository,
) : ViewModel() {
    private val _dummyData = MutableStateFlow("")
    val dummyData: MutableStateFlow<String> get() = _dummyData

    private val _localData = MutableStateFlow("")
    val localData: MutableStateFlow<String> get() = _localData


    private val _focusOnTask = MutableSharedFlow<Triple<Int, Int, Int>>()
    val focusOnTask: MutableSharedFlow<Triple<Int, Int, Int>> get() = _focusOnTask

    private val _onRoutineClick = MutableSharedFlow<Pair<Int, Int>>()
    val onRoutineClick: MutableSharedFlow<Pair<Int, Int>> get() = _onRoutineClick

    private val _onImportanceClick = MutableSharedFlow<Pair<Int, Int>>()
    val onImportanceClick: MutableSharedFlow<Pair<Int, Int>> get() = _onImportanceClick


    // cate1
    private val cate1TaskList : MutableList<MainTaskData> = mutableListOf()

    private val _addCate1SubTaskFlow = MutableSharedFlow<Pair<Int, Int>?>()
    val addCate1SubTaskFlow: MutableSharedFlow<Pair<Int, Int>?> get() = _addCate1SubTaskFlow

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

    private val _addCate2SubTaskFlow = MutableSharedFlow<Pair<Int, Int>?>()
    val addCate2SubTaskFlow: MutableSharedFlow<Pair<Int, Int>?> get() = _addCate2SubTaskFlow

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

    private val _addCate3SubTaskFlow = MutableSharedFlow<Pair<Int, Int>?>()
    val addCate3SubTaskFlow: MutableSharedFlow<Pair<Int, Int>?> get() = _addCate3SubTaskFlow

    private val _deleteCate3SubTask = MutableSharedFlow<Int?>()
    val deleteCate3SubTask: MutableSharedFlow<Int?> get() = _deleteCate3SubTask

    private val _cate3TaskBlank = MutableStateFlow(true)
    val cate3TaskBlank: MutableStateFlow<Boolean> get() = _cate3TaskBlank

    private val _cate3MainTaskChecked = MutableSharedFlow<Pair<Int, Boolean>?>()
    val cate3MainTaskChecked: MutableSharedFlow<Pair<Int, Boolean>?> get() = _cate3MainTaskChecked

    private val _cate3SubTaskChecked = MutableSharedFlow<Triple<Int, Int, Boolean>?>()
    val cate3SubTaskChecked: MutableSharedFlow<Triple<Int, Int, Boolean>?> get() = _cate3SubTaskChecked


    fun routineTabClick(categoryIdx: Int, mainTaskIdx: Int) = viewModelScope.launch {
        _onRoutineClick.emit(Pair(categoryIdx, mainTaskIdx))
    }

    fun importanceTabClick(categoryIdx: Int, mainTaskIdx: Int) = viewModelScope.launch {
        _onImportanceClick.emit(Pair(categoryIdx, mainTaskIdx))
    }


    fun getCategoryTaskList(date: String) = viewModelScope.launch {
        val result = taskRepository.getDetailTask(9, date)

        if(result.isSuccessful) {
            result.body()?.let {

            }
        }
    }

    fun addMainTask(mainTaskContent: String, categoryIdx: Int) = viewModelScope.launch {
        val newMainTask = MainTaskData(
            taskContent = mainTaskContent,
            completed = false,
            subTasks = mutableListOf()
        )

        when(categoryIdx) {
            0 -> cate1TaskList.add(newMainTask)
            1 -> cate2TaskList.add(newMainTask)
            else -> cate3TaskList.add(newMainTask)
        }
    }

    fun addSubTaskLayout(mainTaskIdx: Int, categoryIdx: Int) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> _addCate1SubTaskFlow.emit(Pair(categoryIdx, mainTaskIdx))
            1 -> _addCate2SubTaskFlow.emit(Pair(categoryIdx, mainTaskIdx))
            else -> _addCate3SubTaskFlow.emit(Pair(categoryIdx, mainTaskIdx))
        }
    }

    fun addSubTask(categoryIdx: Int, mainTaskIdx: Int, subTaskContent: String) = viewModelScope.launch {
        val originalListValue = when(categoryIdx) {
            0 -> cate1TaskList
            1 -> cate2TaskList
            else -> cate3TaskList
        }

        val newSubTask = SubTaskData(
            content = subTaskContent,
            completed = false
        )
        val newSubTaskList = originalListValue[mainTaskIdx].subTasks
        newSubTaskList?.add(newSubTask)

        when(categoryIdx) {
            0 -> cate1TaskList[mainTaskIdx].subTasks = newSubTaskList
            1 -> cate2TaskList[mainTaskIdx].subTasks = newSubTaskList
            else -> cate3TaskList[mainTaskIdx].subTasks = newSubTaskList
        }

        //TODO 서버연결 후 서브테스크 내용 보내기
    }

    fun checkMainTask(categoryIdx: Int, mainTaskIdx: Int, checked: Boolean) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> {
                cate1TaskList[mainTaskIdx].completed = checked
                cate1TaskList[mainTaskIdx].subTasks?.forEach {
                    it.completed = checked
                }

                _cate1MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate1TaskList[mainTaskIdx].subTasks?.forEachIndexed { index, _ ->
                    _cate1SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
            1 -> {
                cate2TaskList[mainTaskIdx].completed = checked
                cate2TaskList[mainTaskIdx].subTasks?.forEach {
                    it.completed = checked
                }

                _cate2MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate2TaskList[mainTaskIdx].subTasks?.forEachIndexed { index, _ ->
                    _cate2SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
            else -> {
                cate3TaskList[mainTaskIdx].completed = checked
                cate3TaskList[mainTaskIdx].subTasks?.forEach {
                    it.completed = checked
                }

                _cate3MainTaskChecked.emit(Pair(mainTaskIdx, checked))

                // 메인 테스크 체크시 서브 테스크들 체크
                cate3TaskList[mainTaskIdx].subTasks?.forEachIndexed { index, _ ->
                    _cate3SubTaskChecked.emit(Triple(mainTaskIdx, index, checked))
                }
            }
        }
    }

    fun checkSubTask(categoryIdx: Int, mainTaskIdx: Int, subTaskIdx: Int, checked: Boolean) = viewModelScope.launch {
        when(categoryIdx) {
            0 -> {
                cate1TaskList[mainTaskIdx].subTasks?.get(subTaskIdx)?.completed = checked
                _cate1SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

//                Log.d("Logd", cate1TaskList[mainTaskIdx].subTasks.toString())

                val isAllItemTrue = cate1TaskList[mainTaskIdx].subTasks?.all { it.completed == true } == true
                val isAllItemFalse = cate1TaskList[mainTaskIdx].subTasks?.all { it.completed == false } == true

                if(isAllItemTrue || isAllItemFalse) {
                    cate1TaskList[mainTaskIdx].completed = isAllItemTrue
                    _cate1MainTaskChecked.emit(Pair(mainTaskIdx, isAllItemTrue))
                }
            }
            1 -> {
                cate2TaskList[mainTaskIdx].subTasks?.get(subTaskIdx)?.completed = checked
                _cate2SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

                val isAllItemTrue = cate2TaskList[mainTaskIdx].subTasks?.all { it.completed == true } == true
                val isAllItemFalse = cate2TaskList[mainTaskIdx].subTasks?.all { it.completed == false } == true

                if(isAllItemTrue || isAllItemFalse) {
                    cate2TaskList[mainTaskIdx].completed = isAllItemTrue
                    _cate2MainTaskChecked.emit(Pair(mainTaskIdx, isAllItemTrue))
                }
            }
            else -> {
                cate3TaskList[mainTaskIdx].subTasks?.get(subTaskIdx)?.completed = checked
                _cate3SubTaskChecked.emit(Triple(mainTaskIdx, subTaskIdx, checked))

                val isAllItemTrue = cate3TaskList[mainTaskIdx].subTasks?.all { it.completed == true } == true
                val isAllItemFalse = cate3TaskList[mainTaskIdx].subTasks?.all { it.completed == false } == true

                if(isAllItemTrue || isAllItemFalse) {
                    cate3TaskList[mainTaskIdx].completed = isAllItemTrue
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