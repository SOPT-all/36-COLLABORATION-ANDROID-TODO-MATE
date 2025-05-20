package com.example.myapplication.presentation.home.routine

import androidx.compose.runtime.Composable
import com.example.myapplication.presentation.home.routine.component.TodomatePickerContainer

@Composable
fun RoutineEndScreen(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
) {
    TodomatePickerContainer(
        title = "종료 날짜",
        onLeftClick = onLeftClick,
        onRightClick = onRightClick,
        showLeftButton = true,
        showRightButton = true
    )
}