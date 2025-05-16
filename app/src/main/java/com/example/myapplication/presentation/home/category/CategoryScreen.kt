package com.example.myapplication.presentation.home.category

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
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
import com.example.myapplication.presentation.home.category.component.CategoryItem
import com.example.myapplication.presentation.util.keyboard.KeyboardVisibilityUtils

@Composable
fun CategoryScreen() {
    MainListItem()
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun MainListItem() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        CategoryItem(0)
        Spacer(Modifier.height(20.dp))
        CategoryItem(1)
        Spacer(Modifier.height(20.dp))
        CategoryItem(2)
    }
}


fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}