package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myapplication.data.interceptor.ServicePool
import com.example.myapplication.data.local.datasourceimpl.DummyLocalDataSourceImpl
import com.example.myapplication.data.repositoryimpl.TaskRepositoryImpl
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.presentation.main.component.MainBottomBar
import com.example.myapplication.ui.theme.TodomateTheme

class MainActivity : ComponentActivity() {

    private val service by lazy { ServicePool.taskService }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = TaskRepositoryImpl(service)

        val viewModel = HomeViewModel(repository)

        setContent {

            var keyBoardShow by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                viewModel.keyboardVisible.collect {
                    keyBoardShow = it
                }
            }

            TodomateTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            HomeScreen(
                                viewModel = viewModel
                            )
                        }

                        if(!keyBoardShow) MainBottomBar()
                    }
                }
            }
        }
    }
}
