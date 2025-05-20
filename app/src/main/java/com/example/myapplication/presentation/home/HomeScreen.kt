package com.example.myapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
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
import com.example.myapplication.R
import com.example.myapplication.presentation.custom.HeaderComponent
import com.example.myapplication.presentation.home.calender.CalenderScreen
import com.example.myapplication.presentation.home.category.CategoryScreen
import com.example.myapplication.presentation.home.category.addFocusCleaner
import com.example.myapplication.presentation.home.profile.ProfileBoxComponent
import com.example.myapplication.presentation.home.toolBox.ToolBarScreen
import com.example.myapplication.presentation.main.MainActivity
import com.example.myapplication.presentation.util.compose.findActivity
import com.example.myapplication.presentation.util.keyboard.KeyboardVisibilityUtils

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

    var keyBoardShown by remember { mutableStateOf(false) }

    val addCate1SubTaskFlow by viewModel.addCate1SubTaskFlow.collectAsState(null)
    val addCate2SubTaskFlow by viewModel.addCate2SubTaskFlow.collectAsState(null)
    val addCate3SubTaskFlow by viewModel.addCate3SubTaskFlow.collectAsState(null)

    var targetCategoryIdx by remember { mutableIntStateOf(-1) }
    var targetMainTaskIdx by remember { mutableIntStateOf(-1) }
    var targetSubTaskIdx by remember { mutableIntStateOf(-1) }

    LaunchedEffect(Unit) {
        viewModel.focusOnTask.collect {
            targetCategoryIdx = it.first
            targetMainTaskIdx = it.second
            targetSubTaskIdx = it.third

//            Log.d("Logd", "targetCategoryIdx : $targetCategoryIdx")
//            Log.d("Logd", "targetMainTaskIdx : $targetMainTaskIdx")
//            Log.d("Logd", "targetSubTaskIdx : $targetSubTaskIdx")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate1SubTaskFlow.collect {
            if(addCate1SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            keyBoardShown = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate2SubTaskFlow.collect {
            if(addCate2SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            keyBoardShown = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.addCate3SubTaskFlow.collect {
            if(addCate3SubTaskFlow == null) return@collect

            focusManager.clearFocus()
            keyBoardShown = false
        }
    }

    keyboardVisibilityUtils = KeyboardVisibilityUtils(
        window = activity.window,
        onShowKeyboard = {
            keyBoardShown = true
        },
        onHideKeyboard = {
            focusManager.clearFocus()
            keyBoardShown = false
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(innerPaddingValues)
            .addFocusCleaner(focusManager)
    ) {
        HeaderComponent()

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            item {
                CalenderScreen()
            }

            item {
                CategoryScreen(viewModel)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = if (keyBoardShown) WindowInsets.ime.asPaddingValues().calculateBottomPadding() else 0.dp)
    ) {
        Box(
            modifier.align(Alignment.BottomCenter)
        ) {
            ToolBarScreen(
                viewModel = viewModel,
                isKeyBoardShown = keyBoardShown,
                categoryIdx = targetCategoryIdx,
                mainTaskIdx = targetMainTaskIdx,
                subTaskIdx = targetSubTaskIdx
            )
        }
    }


}
