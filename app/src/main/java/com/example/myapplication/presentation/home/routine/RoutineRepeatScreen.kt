package com.example.myapplication.presentation.home.routine

import androidx.compose.runtime.*
import com.example.myapplication.presentation.component.RepeatCycle
import com.example.myapplication.presentation.component.RepeatCycleSelector

@Composable
fun RoutineRepeatScreen(
    onLeftClick: () -> Unit,
    onConfirmClick: (RepeatCycle) -> Unit
) {
    var selected by remember { mutableStateOf<RepeatCycle?>(null) }

    RepeatCycleSelector(
        selected = selected,
        onSelect = { selected = it },
        onLeftClick = onLeftClick,
        onConfirmClick = {
            selected?.let { onConfirmClick(it) }
        }
    )
}


