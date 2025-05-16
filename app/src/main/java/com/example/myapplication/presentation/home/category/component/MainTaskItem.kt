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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.BlueCategory3
import com.example.myapplication.ui.theme.GreenCategory1
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.PurpleCategory2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTaskItem(cateName: String, categoryIdx: Int, finishEditCallback: (String) -> Unit) {
    val typoProvider = LocalTodomateTypographyProvider.current
    var cateValue by remember { mutableStateOf("") }

    // TF
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    var focusState by remember { mutableStateOf(false) }

    val detailCateList = remember { mutableStateListOf<String>() }

    val categoryColor = when(categoryIdx) {
        0 -> GreenCategory1
        1 -> PurpleCategory2
        else -> BlueCategory3
    }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_weekday_unchecked),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
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
                        if (!it.isFocused) {
                            finishEditCallback(cateValue)
                        }
                    }
                    .focusRequester(focusRequester),
            )
        }

        Spacer(Modifier.height(8.dp))

        if (!focusState && cateValue.isNotEmpty() && detailCateList.isEmpty()) {
            detailCateList.add("")
        }

        detailCateList.forEachIndexed { index, _ ->
            SubTaskItem(detailCateList[index], categoryIdx) { editText ->
                detailCateList[index] = editText
            }
        }
    }
}