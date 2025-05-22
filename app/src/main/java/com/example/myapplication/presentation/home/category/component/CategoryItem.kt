package com.example.myapplication.presentation.home.category.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.presentation.home.HomeViewModel
import com.example.myapplication.ui.theme.BlueCategory3
import com.example.myapplication.ui.theme.GreenCategory1
import com.example.myapplication.ui.theme.Grey10
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.PurpleCategory2

@Composable
fun CategoryItem(categoryIdx: Int, viewModel: HomeViewModel) {
    val typoProvider = LocalTodomateTypographyProvider.current

    val cateList = remember { mutableStateListOf<String>() }

    val categoryTitle = when(categoryIdx) {
        0 -> stringResource(R.string.category1_title)
        1 -> stringResource(R.string.category2_title)
        else -> stringResource(R.string.category3_title)
    }

    val categoryColor = when(categoryIdx) {
        0 -> GreenCategory1
        1 -> PurpleCategory2
        else -> BlueCategory3
    }

    val categoryKey = when(categoryIdx) {
        0 -> "CATEGORY1"
        1 -> "CATEGORY2"
        else -> "CATEGORY3"
    }

    LaunchedEffect(Unit) {
        viewModel.settingCate1TaskList.collect {
            if(it.isNotEmpty()) {
                if(it[0].category == categoryKey) {
                    it.forEach {
                        cateList.add(it.taskContent ?: "")
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.settingCate2TaskList.collect {
            if(it.isNotEmpty()) {
                if(it[0].category == categoryKey) {
                    it.forEach {
                        cateList.add(it.taskContent ?: "")
                    }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.settingCate3TaskList.collect {
            if(it.isNotEmpty()) {
                if(it[0].category == categoryKey) {
                    it.forEach {
                        cateList.add(it.taskContent ?: "")
                    }
                }
            }
        }
    }

    Row (
        modifier = Modifier
            .width(126.dp)
            .height(36.dp)
            .background(Grey10, shape = RoundedCornerShape(35.dp))
            .clickable {
                cateList.forEach {
                    if(it.isEmpty() || cateList.size > 10) {
                        return@clickable
                    }
                }
                cateList.add("")
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_lock),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = categoryTitle,
            style = typoProvider.cap_bold_12,
            color = categoryColor
        )

        Spacer(Modifier.width(8.dp))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_add),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
    }

    Spacer(modifier = Modifier.height(4.dp))

    cateList.forEachIndexed { index, _ ->
        MainTaskItem(
            viewModel = viewModel,
            cateName = cateList[index],
            categoryIdx = categoryIdx,
            mainTaskIdx = index
        ) { editText ->
            cateList[index] = editText
            viewModel.addMainTask(cateList[index], categoryIdx)
        }
    }
}