package com.example.myapplication.presentation.home.category.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.presentation.util.compose.noRippleClickable
import com.example.myapplication.ui.theme.BlueCategory3
import com.example.myapplication.ui.theme.GreenCategory1
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.PurpleCategory2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTaskItem(
    viewModel: HomeViewModel,
    cateName: String,
    categoryIdx: Int,
    mainTaskIdx: Int,
    finishEditCallback: (String) -> Unit
) {
    val typoProvider = LocalTodomateTypographyProvider.current
    var cateValue by remember { mutableStateOf("") }

    val addCate1SubTaskFlow by viewModel.addCate1SubTaskFlow.collectAsStateWithLifecycle(null)

    var itemChecked by remember { mutableStateOf(false) }
    var iconStatus by remember { mutableStateOf(R.drawable.icon_weekday_unchecked) }

    // TF
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var focusState by remember { mutableStateOf(false) }

    val subCateList = remember { mutableStateListOf<String>() }

    val categoryColor = when(categoryIdx) {
        0 -> GreenCategory1
        1 -> PurpleCategory2
        else -> BlueCategory3
    }

    LaunchedEffect(cateValue) {
        if (cateValue.isEmpty()) {
            viewModel.taskIsBlank(true)
        } else {
            viewModel.taskIsBlank(false)
        }
    }

    LaunchedEffect(addCate1SubTaskFlow) {
        if(addCate1SubTaskFlow == null) return@LaunchedEffect
        if(addCate1SubTaskFlow == mainTaskIdx) {
            subCateList.add("")
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



    Column {
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
                    .size(20.dp)
                    .noRippleClickable {
                        itemChecked = !itemChecked
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            BasicTextField(
                value = cateValue,
                onValueChange = { cateValue = it },
                enabled = cateName.isEmpty(),
                singleLine = true,
                textStyle = typoProvider.body_reg_14,
                modifier = Modifier
                    .weight(1f)
                    .indicatorLine(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.colors(
                            unfocusedIndicatorColor =
                                if(focusState || cateValue.isEmpty()) {
                                    categoryColor
                                } else {
                                    Color.Transparent
                                }
                        )
                    )
                    .onFocusChanged {
                        focusState = it.isFocused
                        if (!it.isFocused && cateValue.isNotEmpty()) {
                            finishEditCallback(cateValue)
                        }
                    }
                    .focusRequester(focusRequester),
            )
        }

        Spacer(Modifier.height(8.dp))

        subCateList.forEachIndexed { index, _ ->
            SubTaskItem(subCateList[index], categoryIdx) { editText ->
                subCateList[index] = editText
            }
        }
    }
}