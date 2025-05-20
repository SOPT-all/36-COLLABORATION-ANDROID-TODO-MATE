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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButton
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButtonStatus
import com.example.myapplication.presentation.home.toolBox.component.ToolBarButtonType
import com.example.myapplication.ui.theme.Grey10

@Composable
fun ToolBarScreen(
    viewModel: HomeViewModel,
    categoryIdx: Int,
    mainTaskIdx: Int,
    subTaskIdx: Int,
    isKeyBoardShown: Boolean
) {
    var toolBarDetailButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }
    var toolBarRoutineButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }
    var toolBarImportanceButtonStatus by remember { mutableStateOf(ToolBarButtonStatus.ON) }

    val task1IsBlank by viewModel.cate1TaskBlank.collectAsStateWithLifecycle()
    val task2IsBlank by viewModel.cate2TaskBlank.collectAsStateWithLifecycle()
    val task3IsBlank by viewModel.cate3TaskBlank.collectAsStateWithLifecycle()

    LaunchedEffect(task1IsBlank) {
        if(task1IsBlank) {
            toolBarDetailButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarRoutineButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarImportanceButtonStatus = ToolBarButtonStatus.UNAVAILABLE
        } else {
            toolBarDetailButtonStatus = ToolBarButtonStatus.OFF
            toolBarRoutineButtonStatus = ToolBarButtonStatus.OFF
            toolBarImportanceButtonStatus = ToolBarButtonStatus.OFF
        }
    }

    LaunchedEffect(task2IsBlank) {
        if(task2IsBlank) {
            toolBarDetailButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarRoutineButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarImportanceButtonStatus = ToolBarButtonStatus.UNAVAILABLE
        } else {
            toolBarDetailButtonStatus = ToolBarButtonStatus.OFF
            toolBarRoutineButtonStatus = ToolBarButtonStatus.OFF
            toolBarImportanceButtonStatus = ToolBarButtonStatus.OFF
        }
    }

    LaunchedEffect(task3IsBlank) {
        if(task3IsBlank) {
            toolBarDetailButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarRoutineButtonStatus = ToolBarButtonStatus.UNAVAILABLE
            toolBarImportanceButtonStatus = ToolBarButtonStatus.UNAVAILABLE
        } else {
            toolBarDetailButtonStatus = ToolBarButtonStatus.OFF
            toolBarRoutineButtonStatus = ToolBarButtonStatus.OFF
            toolBarImportanceButtonStatus = ToolBarButtonStatus.OFF
        }
    }

    if(isKeyBoardShown) {
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
                    ToolBarButtonStatus.OFF -> {
                        toolBarDetailButtonStatus = ToolBarButtonStatus.ON

                        viewModel.addSubTaskLayout(mainTaskIdx = mainTaskIdx, categoryIdx = categoryIdx)
                    }
                    else -> {}
                }
            }

            Spacer(Modifier.width(4.dp))

            ToolBarButton(ToolBarButtonType.Routine, toolBarRoutineButtonStatus) {
                when(toolBarRoutineButtonStatus) {
                    ToolBarButtonStatus.OFF -> {
                        toolBarRoutineButtonStatus = ToolBarButtonStatus.ON
                    }
                    else -> {}
                }
            }

            Spacer(Modifier.width(4.dp))

            ToolBarButton(ToolBarButtonType.Importance, toolBarImportanceButtonStatus) {
                when(toolBarImportanceButtonStatus) {
                    ToolBarButtonStatus.OFF -> {
                        toolBarImportanceButtonStatus = ToolBarButtonStatus.ON
                    }
                    else -> {}
                }
            }

            Spacer(Modifier.width(8.dp))

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_delete),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
    }
}