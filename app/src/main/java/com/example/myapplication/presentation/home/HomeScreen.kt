package com.example.myapplication.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.custom.DashLine
import com.example.myapplication.presentation.custom.HeaderComponent
import com.example.myapplication.presentation.home.calender.CalenderScreen
import com.example.myapplication.presentation.home.category.CategoryScreen
import com.example.myapplication.presentation.home.etc.EtcScreen
import com.example.myapplication.presentation.home.importance.ImportanceScreen
import com.example.myapplication.presentation.home.routine.RoutineScreen
import com.example.myapplication.presentation.home.toolBox.ToolBarScreen
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.presentation.main.component.MainBottomBar
import com.example.myapplication.presentation.util.compose.addFocusCleaner
import com.example.myapplication.presentation.util.compose.findActivity
import com.example.myapplication.presentation.util.keyboard.KeyboardVisibilityUtils
import com.example.myapplication.ui.theme.White

private lateinit var keyboardVisibilityUtils: KeyboardVisibilityUtils

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val activity = context.findActivity() as MainActivity

    var targeted by remember { mutableStateOf(false) }
    var showRoutineLayout by remember { mutableStateOf(false) }
    var showImportanceLayout by remember { mutableStateOf(false) }

    val addCate1SubTaskFlow by viewModel.addCate1SubTaskFlow.collectAsState(null)
    val addCate2SubTaskFlow by viewModel.addCate2SubTaskFlow.collectAsState(null)
    val addCate3SubTaskFlow by viewModel.addCate3SubTaskFlow.collectAsState(null)

    var targetCategoryIdx by remember { mutableIntStateOf(-1) }
    var targetMainTaskIdx by remember { mutableIntStateOf(-1) }
    var targetSubTaskIdx by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        viewModel.getCategoryTaskList("2025-05-23")
    }

    LaunchedEffect(Unit) {
        viewModel.focusOnTask.collect {
            targetCategoryIdx = it.first
            targetMainTaskIdx = it.second
            targetSubTaskIdx = it.third
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate1SubTaskFlow.collect {
            if(addCate1SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            targeted = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate2SubTaskFlow.collect {
            if(addCate2SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            targeted = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate3SubTaskFlow.collect {
            if(addCate3SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            targeted = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onRoutineClick.collect {
            focusManager.clearFocus()
            showRoutineLayout = true
            showImportanceLayout = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onImportanceClick.collect {
            focusManager.clearFocus()
            showImportanceLayout = true
            showRoutineLayout = false
        }
    }

    keyboardVisibilityUtils = KeyboardVisibilityUtils(
        window = activity.window,
        onShowKeyboard = {
            targeted = true
            showRoutineLayout = false
            showImportanceLayout = false

            viewModel.keyBoardVisible(true)
        },
        onHideKeyboard = {
            focusManager.clearFocus()
            if(!showRoutineLayout && !showImportanceLayout) {
                targeted = false
            }

            viewModel.keyBoardVisible(false)
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .addFocusCleaner(focusManager)
    ) {
        HeaderComponent()

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            item {
                CalenderScreen(viewModel)
            }

            item {
                CategoryScreen(viewModel)
            }

            item {
                Box(
                    Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 22.dp)
                ) {
                    DashLine()
                }

            }

            item {
                EtcScreen()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = if (targeted) WindowInsets.ime.asPaddingValues().calculateBottomPadding() else 0.dp)
        ) {
            Column (
                modifier.align(Alignment.BottomCenter)
            ) {
                ToolBarScreen(
                    viewModel = viewModel,
                    isKeyBoardShown = targeted,
                    categoryIdx = targetCategoryIdx,
                    mainTaskIdx = targetMainTaskIdx,
                    subTaskIdx = targetSubTaskIdx
                )

                if(showRoutineLayout || showImportanceLayout) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(268.dp)
                            .background(White)
                    ) {
                        if(showRoutineLayout) {
                            RoutineScreen {
                                showRoutineLayout = false
                                targeted = false
                            }
                        }

                        if(showImportanceLayout) {
                            ImportanceScreen {
                                showImportanceLayout = false
                                targeted = false
                            }
                        }
                    }
                }
            }
        }
    }
}
