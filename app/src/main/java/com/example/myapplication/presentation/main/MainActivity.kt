package com.example.myapplication.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myapplication.data.interceptor.ServicePool
import com.example.myapplication.data.local.datasource.DummyLocalDataSource
import com.example.myapplication.data.local.datasourceimpl.DummyLocalDataSourceImpl
import com.example.myapplication.data.remote.datasourceimpl.DummyRemoteDataSourceImpl
import com.example.myapplication.data.repositoryimpl.DummyRepositoryImpl
import com.example.myapplication.presentation.home.HomeScreen
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val service by lazy { ServicePool.dummyService }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val remoteDataSource = DummyRemoteDataSourceImpl(service)
        val repository = DummyRepositoryImpl(remoteDataSource,DummyLocalDataSourceImpl(this))

        val viewModel = HomeViewModel(repository)

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        innerPaddingValues = innerPadding,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}
