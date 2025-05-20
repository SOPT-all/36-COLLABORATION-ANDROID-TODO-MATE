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
fun RoutineScreen(
    onFinishCallback: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    var selectedCycle by remember { mutableStateOf<RepeatCycle?>(null) }

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
                onLeftClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                },
                onConfirmClick = {
                    //FIXME 데이터가 없움
                    onFinishCallback.invoke()
//                    selectedCycle?.let { cycle ->
//                        // 완료
//                        onFinishCallback.invoke()
//                    }
                }
            )
        }
    }
}
