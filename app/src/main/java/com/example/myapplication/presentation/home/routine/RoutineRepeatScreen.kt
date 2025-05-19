package com.example.myapplication.presentation.home.routine

import androidx.compose.runtime.*
import com.example.myapplication.presentation.component.RepeatCycle
import com.example.myapplication.presentation.component.RepeatCycleSelector

@Composable
fun RoutineRepeatScreen(
    selected: RepeatCycle,
    onSelect: (RepeatCycle) -> Unit,
    onLeftClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    RepeatCycleSelector(
        selected = selected,
        onSelect = onSelect,
        onLeftClick = onLeftClick,
        onConfirmClick = onConfirmClick
    )
}

