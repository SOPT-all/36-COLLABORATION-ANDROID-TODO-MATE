package com.example.myapplication.presentation.home.category.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.presentation.util.compose.noRippleClickable
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.BlueCategory3
import com.example.myapplication.ui.theme.GreenCategory1
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.PurpleCategory2
import com.example.myapplication.ui.theme.Red10

var isFirstEnterAndFocus = true
var hasBeenContentChanged = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubTaskItem(
    viewModel: HomeViewModel,
    subTaskName: String,
    mainTaskIdx: Int,
    categoryIdx: Int,
    subTaskIdx: Int,
    finishEditCallback: (String) -> Unit
) {
    val typoProvider = LocalTodomateTypographyProvider.current
    var subCateValue by remember { mutableStateOf("") }

    var itemChecked by remember { mutableStateOf(false) }
    var iconStatus by remember { mutableStateOf(R.drawable.icon_weekday_unchecked) }

    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var focusState by remember { mutableStateOf(false) }

    val categoryColor = when(categoryIdx) {
        0 -> GreenCategory1
        1 -> PurpleCategory2
        else -> BlueCategory3
    }

    val cate1SubTaskChecked by viewModel.cate1SubTaskChecked.collectAsStateWithLifecycle(null)
    val cate2SubTaskChecked by viewModel.cate2SubTaskChecked.collectAsStateWithLifecycle(null)
    val cate3SubTaskChecked by viewModel.cate3SubTaskChecked.collectAsStateWithLifecycle(null)

    LaunchedEffect(cate1SubTaskChecked) {
        if(cate1SubTaskChecked == null) return@LaunchedEffect

        if(cate1SubTaskChecked!!.first == mainTaskIdx && cate1SubTaskChecked!!.second == subTaskIdx) {
            itemChecked = cate1SubTaskChecked!!.third
        }
    }

    LaunchedEffect(cate2SubTaskChecked) {
        if(cate2SubTaskChecked == null) return@LaunchedEffect

        if(cate2SubTaskChecked!!.first == mainTaskIdx && cate2SubTaskChecked!!.second == subTaskIdx) {
            itemChecked = cate2SubTaskChecked!!.third
        }
    }

    LaunchedEffect(cate3SubTaskChecked) {
        if(cate3SubTaskChecked == null) return@LaunchedEffect

        if(cate3SubTaskChecked!!.first == mainTaskIdx && cate3SubTaskChecked!!.second == subTaskIdx) {
            itemChecked = cate3SubTaskChecked!!.third
        }
    }

    LaunchedEffect(itemChecked) {
        iconStatus = if(itemChecked) {
            when(categoryIdx) {
                0 -> R.drawable.icon_weekday_checked
                1 -> R.drawable.icon_category2_checked
                else -> R.drawable.icon_category3_checked
            }
        } else {
            R.drawable.icon_weekday_unchecked
        }
    }

    LaunchedEffect(subCateValue) {
        if(hasBeenContentChanged == false) {
            hasBeenContentChanged = subCateValue.isNotEmpty()
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = iconStatus),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(16.dp)
                        .noRippleClickable {
                            if(!focusState) {
                                itemChecked = !itemChecked
                                viewModel.checkSubTask(categoryIdx, mainTaskIdx, subTaskIdx, itemChecked)
                            }
                        }
                )

                Spacer(modifier = Modifier.width(8.dp))

                BasicTextField(
                    value = subCateValue,
                    onValueChange = { subCateValue = it },
                    enabled = subTaskName.isEmpty(),
                    singleLine = true,
                    textStyle = typoProvider.cap_reg_10,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if(subCateValue.isEmpty()) return@KeyboardActions

                            viewModel.addSubTaskLayout(mainTaskIdx = mainTaskIdx, categoryIdx)
                        }
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .indicatorLine(
                            enabled = true,
                            isError = false,
                            interactionSource = interactionSource,
                            colors = TextFieldDefaults.colors(
                                cursorColor = Black,
                                unfocusedIndicatorColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent
                            )
                        )
                        .onFocusChanged {
                            if(isFirstEnterAndFocus) {
                                isFirstEnterAndFocus = false
                                return@onFocusChanged
                            }

                            focusState = it.isFocused
                            if(it.isFocused) {
                                viewModel.focusOnTask(categoryIdx, mainTaskIdx, subTaskIdx)
                            } else {
//                                if(subCateValue.isEmpty() && !hasBeenContentChanged) {
//                                    viewModel.deleteSubItemLayout(categoryIdx, mainTaskIdx)
//                                } else {
//                                    finishEditCallback(subCateValue)

//                                    isFirstEnterAndFocus = true
//                                    hasBeenContentChanged = false
//                                }

                                finishEditCallback(subCateValue)
                                isFirstEnterAndFocus = true
                            }
                        }
                        .focusRequester(focusRequester),
                )
            }

            Spacer(Modifier.height(4.dp))

            if(focusState || subCateValue.isEmpty()) {
                HorizontalDivider(thickness = 1.dp, color = categoryColor)
            }

        }
    }
}