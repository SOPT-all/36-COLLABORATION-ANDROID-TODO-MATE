package com.example.myapplication.presentation.util.dialog

import androidx.compose.runtime.Composable

/** 세부 테스트, 삭제 모달 **/
@Composable
fun AlertDialog(
    titleText: String,
    contentText: String,
    setShowDialog: (Boolean) -> Unit = { },
    btnP1Text: String,
    btnP2Text: String,
    btn1Click: () -> Unit = { },
    btn2Click: () -> Unit = { },
) {

}