package com.example.myapplication.presentation.home.category

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.presentation.home.category.component.CategoryItem

@Composable
fun CategoryScreen(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        CategoryItem(0, viewModel)
        Spacer(Modifier.height(20.dp))
        CategoryItem(1, viewModel)
        Spacer(Modifier.height(20.dp))
        CategoryItem(2, viewModel)
    }
}