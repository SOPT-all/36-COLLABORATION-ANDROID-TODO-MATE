package com.example.myapplication.presentation.home.calender

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.YearMonth

data class CalendarState(
    val currentYearMonth: YearMonth = YearMonth.now(),
    val today: LocalDate = LocalDate.now(),
    val selectedDate: LocalDate = LocalDate.now()
)

class CalendarViewModel : ViewModel() {
    private val today = LocalDate.now()

    private val _currentWeekStart = MutableStateFlow(getStartOfWeek(today))  // 현재 보고 있는 주의 시작
    val currentWeekStart = _currentWeekStart.asStateFlow()

    private val _calendarState = MutableStateFlow(
        CalendarState(
            currentYearMonth = YearMonth.from(today),
            today = today
        )
    )
    val calendarState = _calendarState.asStateFlow()

    // 사용자가 선택(클릭)한 날짜
    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    // 주어진 날짜가 속한 주의 월요일을 반환하는 함수
    fun getStartOfWeek(date: LocalDate): LocalDate {
        val dayOfWeek = date.dayOfWeek.value  // 월=1 ~ 일=7
        val daysToSubstract = if (dayOfWeek == 1) 0 else dayOfWeek - 1  // 월요일까지 거슬러 올라가기 위해 빼야 하는 수
        return date.minusDays(daysToSubstract.toLong())
    }

    // 이전 주로 이동하는 함수
    fun goToPreviousWeek() {
        _currentWeekStart.value = _currentWeekStart.value.minusWeeks(1)
        updateYearMonth()
    }

    // 다음 주로 이동하는 함수
    fun goToNextWeek() {
        _currentWeekStart.value = _currentWeekStart.value.plusWeeks(1)
        updateYearMonth()
    }

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
        Log.d("Calendar", "Selected date: $date")
    }

    private fun updateYearMonth() {
        val newMonth = YearMonth.from(_currentWeekStart.value)
        _calendarState.value = _calendarState.value.copy(currentYearMonth = newMonth)
    }
}