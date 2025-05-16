package com.example.myapplication.presentation.home.toolBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButton
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButtonStatus
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButtonType
import com.example.myapplication.ui.theme.Grey10

@Composable
fun ToolBarScreen() {
    var toolBarDetailButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }
    var toolBarRoutineButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }
    var toolBarImportanceButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Grey10, shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .padding(vertical = 9.dp)
            .padding(start = 24.dp)

    ) {
        ToolBarButton(ToolBarButtonType.Details, toolBarDetailButtonStatus) {
            when(toolBarDetailButtonStatus) {
                ToolBarButtonStatus.ON -> {
                    toolBarDetailButtonStatus = ToolBarButtonStatus.OFF
                }
                else -> {}
            }
        }

        Spacer(Modifier.width(4.dp))

        ToolBarButton(ToolBarButtonType.Routine, toolBarRoutineButtonStatus) {
            when(toolBarRoutineButtonStatus) {
                ToolBarButtonStatus.ON -> {
                    toolBarRoutineButtonStatus = ToolBarButtonStatus.OFF
                }
                else -> {}
            }
        }

        Spacer(Modifier.width(4.dp))

        ToolBarButton(ToolBarButtonType.Importance, toolBarImportanceButtonStatus) {
            when(toolBarImportanceButtonStatus) {
                ToolBarButtonStatus.ON -> {
                    toolBarImportanceButtonStatus = ToolBarButtonStatus.OFF
                }
                else -> {}
            }
        }
    }
}