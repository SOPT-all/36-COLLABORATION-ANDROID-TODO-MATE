package com.example.myapplication.presentation.home.routine

import androidx.compose.runtime.Composable
import com.example.myapplication.presentation.component.TodomatePickerContainer

@Composable
fun RoutineStartScreen(
    onLeftClick: () -> Unit = {},
    onRightClick: () -> Unit = {},
) {

    TodomatePickerContainer(
        title = "시작 날짜",
        onLeftClick = onLeftClick,
        onRightClick = onRightClick,
        showLeftButton = false,
        showRightButton = true
    )
}