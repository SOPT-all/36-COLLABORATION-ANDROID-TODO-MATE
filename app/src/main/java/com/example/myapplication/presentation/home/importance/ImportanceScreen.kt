package com.example.myapplication.presentation.home.importance

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.presentation.component.ImportanceCycle
import com.example.myapplication.presentation.component.ImportanceSelector

@Composable
fun ImportanceScreen(
    onConfirmClick: () -> Unit
) {
    var selectedCycle by remember { mutableStateOf<ImportanceCycle?>(null) }

    ImportanceSelector(
        selected = selectedCycle,
        onSelect = { selectedCycle = it },
        onLeftClick = {  },
        onConfirmClick = {
            onConfirmClick.invoke()
        }
    )
}
