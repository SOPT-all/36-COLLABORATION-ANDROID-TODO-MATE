package com.example.myapplication.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    innerPaddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val dummyData by viewModel.dummyData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.dummyFun()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(innerPaddingValues)
    ) {
        Text(text = "닉네임은 $dummyData")
    }
}
