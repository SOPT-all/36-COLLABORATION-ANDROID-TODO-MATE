package com.example.myapplication.presentation.home.calender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Blue20
import com.example.myapplication.ui.theme.DarkGrey30
import com.example.myapplication.ui.theme.GreenCategory1
import com.example.myapplication.ui.theme.Grey40
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.Red10
import com.example.myapplication.ui.theme.White

@Composable
fun DayItemComponent(
    onClick: () -> Unit,
    isSelected: Boolean = false,
    isToday: Boolean = false,
    dayText: Weekday,
    dayNumber: Int,
    leftTaskNum: Int = 0,  // 남은 할 일 개수
    doneTaskNum: Int = 0,  // 완료된 할 일 개수
) {
    val typography = LocalTodomateTypographyProvider.current

    val dayTextColor = when (dayText) {
        Weekday.SATURDAY -> Blue20
        Weekday.SUNDAY -> Red10
        else -> DarkGrey30
    }

    val dayNumberColor = if (isSelected) {
        when (dayText) {
            Weekday.SATURDAY -> Blue20
            Weekday.SUNDAY -> Red10
            else -> White
        }
    } else {
        when (dayText) {
            Weekday.SATURDAY -> Blue20
            Weekday.SUNDAY -> Red10
            else -> DarkGrey30
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(
            text = dayText.day,
            style = typography.cap_med_10,
            color = dayTextColor
        )

        Spacer(modifier = Modifier.height(6.dp))

        // 클로버 영역
        Box(
            contentAlignment = Alignment.Center
        ) {
            val imageId = if (doneTaskNum > 0 && leftTaskNum == 0) {
                R.drawable.icon_weekday_checked
            } else {
                R.drawable.icon_weekday_unchecked
            }

            val imageColorFilter = if (doneTaskNum > 0 && leftTaskNum > 0) {
                ColorFilter.tint(GreenCategory1)
            } else {
                null
            }

            Image(
                painter = painterResource(imageId),
                contentDescription = stringResource(R.string.icon_weekday),
                modifier = Modifier
                    .size(22.dp),
                colorFilter = imageColorFilter
            )
            if (leftTaskNum != 0) {
                Text(
                    text = leftTaskNum.toString(),
                    style = typography.cap_med_10,
                    color = White
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {
            val dayNumBoxColor = if (isSelected) {
                Black
            } else if (isToday) {
                Grey40
            } else {
                Color.Transparent
            }

            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(dayNumBoxColor)
            )
            Text(
                text = dayNumber.toString(),
                style = typography.cap_med_10,
                color = dayNumberColor
            )
        }
    }
}

@Preview
@Composable
private fun PreviewDayItemComponent() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        // 클릭 여부 관련 예시
        // (1) 오늘 + 클릭된 경우
        DayItemComponent(
            onClick = {},
            isSelected = true,
            isToday = true,
            dayText = Weekday.MONDAY,
            dayNumber = 18
        )
        // (2) 오늘 + 클릭되지 않은 경우
        DayItemComponent(
            onClick = {},
            isSelected = false,
            isToday = true,
            dayText = Weekday.TUESDAY,
            dayNumber = 19
        )
        // (3) 오늘 아님 + 클릭된 경우
        DayItemComponent(
            onClick = {},
            isSelected = true,
            isToday = false,
            dayText = Weekday.WEDNESDAY,
            dayNumber = 20
        )

        // 할 일 개수 관련 예시
        // (1) 입력된 할 일이 없는 경우
        DayItemComponent(
            onClick = {},
            dayText = Weekday.THURSDAY,
            dayNumber = 21
        )
        // (2) 할 일을 입력했으나, 완료된 할 일이 한 개도 없는 경우
        DayItemComponent(
            onClick = {},
            dayText = Weekday.FRIDAY,
            dayNumber = 22,
            leftTaskNum = 3
        )
        // (3) 할 일을 입력했고, 완료된 할 일이 한 개 이상인 경우
        DayItemComponent(
            onClick = {},
            dayText = Weekday.SATURDAY,
            dayNumber = 23,
            leftTaskNum = 3,
            doneTaskNum = 2
        )
        // (4) 할 일을 입력했고, 모든 할 일을 완료한 경우
        DayItemComponent(
            onClick = {},
            dayText = Weekday.SUNDAY,
            dayNumber = 24,
            doneTaskNum = 2
        )
    }
}