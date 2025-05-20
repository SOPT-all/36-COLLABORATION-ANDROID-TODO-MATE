package com.example.myapplication.presentation.home.calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.presentation.home.calender.component.CalendarTitleComponent
import com.example.myapplication.presentation.home.calender.component.DayItemComponent
import com.example.myapplication.presentation.home.profile.PeopleBoxItemComponent
import com.example.myapplication.presentation.home.profile.ProfileBoxComponent
import java.time.DayOfWeek
import java.time.LocalDate

// TODO: 파일명 수정 (CalenderScreen -> CalendarScreen)
@Composable
fun CalenderScreen(
    viewModel: CalendarViewModel = viewModel()
) {
    val calendarState by viewModel.calendarState.collectAsState()
    val currentWeekStart by viewModel.currentWeekStart.collectAsState()
    val weekDates = remember(currentWeekStart) {
        (0..6).map { currentWeekStart.plusDays(it.toLong()) }
    }

    val selectedDate by viewModel.selectedDate.collectAsState()
    val today = remember { LocalDate.now() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // PeopleBox
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(24.dp, 8.dp, 24.dp, 10.dp)
        ) {
            PeopleBoxItemComponent(
                imageId = R.drawable.icon_me,
                nameId = R.string.profile_name
            )
            PeopleBoxItemComponent(
                imageId = R.drawable.icon_more,
                nameId = null
            )
        }

        // ProfileBox
        ProfileBoxComponent(
            modifier = Modifier.padding(28.dp, 30.dp, 24.dp, 24.dp),
            imageId = R.drawable.icon_me,
            nameId = R.string.profile_name
        )

        // Calendar
        CalendarTitleComponent(
            modifier = Modifier.padding(horizontal = 24.dp),
            yearText = calendarState.currentYearMonth.year,
            monthText = calendarState.currentYearMonth.monthValue,
            onLeftClick = { viewModel.goToPreviousWeek() },
            onRightClick = { viewModel.goToNextWeek() }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 26.dp, 24.dp, 34.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            weekDates.forEach { date ->
                DayItemComponent(
                    onClick = { viewModel.updateSelectedDate(date) },
                    isSelected = selectedDate == date,
                    isToday = today == date,
                    dayText = date.dayOfWeek.toWeekday(),
                    dayNumber = date.dayOfMonth,
                )
            }
        }
    }
}

fun DayOfWeek.toWeekday(): Weekday = when (this) {
    DayOfWeek.MONDAY -> Weekday.MONDAY
    DayOfWeek.TUESDAY -> Weekday.TUESDAY
    DayOfWeek.WEDNESDAY -> Weekday.WEDNESDAY
    DayOfWeek.THURSDAY -> Weekday.THURSDAY
    DayOfWeek.FRIDAY -> Weekday.FRIDAY
    DayOfWeek.SATURDAY -> Weekday.SATURDAY
    DayOfWeek.SUNDAY -> Weekday.SUNDAY
}

@Preview
@Composable
private fun PreviewCalendarScreen() {
    CalenderScreen()
}