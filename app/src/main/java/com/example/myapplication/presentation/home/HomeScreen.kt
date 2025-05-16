package com.example.myapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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

    keyboardVisibilityUtils = KeyboardVisibilityUtils(
        window = activity.window,
        onShowKeyboard = {  },
        onHideKeyboard = {
            focusManager.clearFocus()
        }
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(innerPaddingValues)
            .addFocusCleaner(focusManager),
    ) {
        item {
            Spacer(Modifier.height(200.dp))
        }

        item {
            CategoryScreen()
        }

        item {
            ToolBarScreen()
        }
    }
}
