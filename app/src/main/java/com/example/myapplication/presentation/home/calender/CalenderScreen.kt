package com.example.myapplication.presentation.home.calender

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.presentation.home.profile.PeopleBoxItemComponent
import com.example.myapplication.presentation.home.profile.ProfileBoxComponent

// TODO: 파일명 수정 (CalenderScreen -> CalendarScreen)
@Composable
fun CalenderScreen() {
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
            yearText = 2025,
            monthText = 4,
            onLeftClick = {},
            onRightClick = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 26.dp, 24.dp, 34.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Weekday.entries.forEach { date ->
                DayItemComponent(
                    onClick = {},
                    dayText = date,
                    dayNumber = 0,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCalendarScreen() {
    CalenderScreen()
}