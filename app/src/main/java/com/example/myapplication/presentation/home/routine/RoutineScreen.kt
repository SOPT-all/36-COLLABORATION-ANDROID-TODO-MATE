package com.example.myapplication.presentation.home.routine

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.myapplication.presentation.component.RepeatCycle
import kotlinx.coroutines.launch

@Composable
fun RoutineScreen() {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    var selectedCycle by remember { mutableStateOf(RepeatCycle.DAILY) }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth()
    ) { page ->
        when (page) {
            0 -> RoutineStartScreen(
                onLeftClick = {  },
                onRightClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            )
            1 -> RoutineEndScreen(
                onLeftClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                },
                onRightClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(2)
                    }
                }
            )
            2 -> RoutineRepeatScreen(
                selected = selectedCycle,
                onSelect = { selectedCycle = it },
                onLeftClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                },
                onConfirmClick = {}
            )
        }
    }
}
