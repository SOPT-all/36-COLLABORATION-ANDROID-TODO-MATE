package com.example.myapplication.presentation.util.dialog

import androidx.compose.runtime.Composable

/** 삭제 모달 **/
@Composable
fun BoxDialog(
    titleText: String,
    contentText: String,
    setShowDialog: (Boolean) -> Unit = { },
    btnP1Text: String,
    btnP2Text: String,
    btn1Click: () -> Unit = { },
    btn2Click: () -> Unit = { },
) {

}