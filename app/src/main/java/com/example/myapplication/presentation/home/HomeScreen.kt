package com.example.myapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.home.category.CategoryScreen
import com.example.myapplication.presentation.home.category.addFocusCleaner
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

    LaunchedEffect(addCate1SubTaskFlow) {
        if(addCate1SubTaskFlow == null) return@LaunchedEffect

        focusManager.clearFocus()
        keyBoardShown = false
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(innerPaddingValues)
            .addFocusCleaner(focusManager)
    ) {
        item {
            Spacer(Modifier.height(20.dp))
        }

        item {
            CategoryScreen(viewModel)
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
            ToolBarScreen(viewModel, keyBoardShown)
        }
    }
}
