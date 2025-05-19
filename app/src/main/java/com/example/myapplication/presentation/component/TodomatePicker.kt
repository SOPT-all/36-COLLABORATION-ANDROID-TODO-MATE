package com.example.myapplication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.LocalTodomateColorsProvider
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.TodomateTheme
import com.kez.picker.Picker
import com.kez.picker.PickerState

@Composable
fun TodomatePickerContainer(
    title: String,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    showLeftButton: Boolean = true,
    showRightButton: Boolean = true
) {
    val typography = LocalTodomateTypographyProvider.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (showLeftButton) {
                IconButton(onClick = onLeftClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_moveleft),
                        contentDescription = "이전"
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }

            Text(
                text = title,
                style = typography.body_reg_14
            )

            if (showRightButton) {
                IconButton(onClick = onRightClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_moveright),
                        contentDescription = "이후"
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(48.dp))
            }
        }

        TodomatePicker()
    }
}

@Composable
fun TodomatePicker(
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    val years = (2015..2099).map { "${it}년" }
    val months = (1..12).map { "${it}월" }
    val days = (1..31).map { "${it}일" }

    val yearState = remember { PickerState(years.first()) }
    val monthState = remember { PickerState(months.first()) }
    val dayState = remember { PickerState(days.first()) }

    val colors = LocalTodomateColorsProvider.current
    val typography = LocalTodomateTypographyProvider.current

    val textStyle = typography.body_reg_14.copy(color = colors.BlueGrey30)
    val selectedTextStyle = typography.body_med_16.copy(color = colors.Black)

    val itemPadding = PaddingValues(vertical = 10.dp, horizontal = 8.dp)

    val fadingEdgeGradient = Brush.verticalGradient(
        0f to Color.Transparent,
        0.5f to colors.Black,
        1f to Color.Transparent
    )

    val density = LocalDensity.current
    val itemHeight = with(density) {
        selectedTextStyle.fontSize.toDp() +
                itemPadding.calculateTopPadding() +
                itemPadding.calculateBottomPadding()
    }

    val yearWidth = calculateWidth(years, textStyle)
    val monthWidth = calculateWidth(months, textStyle)
    val dayWidth = calculateWidth(days, textStyle)

    Row(
        modifier = modifier
            .background(Color.Transparent),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.Transparent, RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(yearWidth)
            )
            Picker(
                state = yearState,
                items = years,
                visibleItemsCount = 5,
                modifier = Modifier.width(yearWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = colors.BlueGrey30,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(monthWidth)
                    .background(Color.Transparent, RoundedCornerShape(12.dp))            )
            Picker(
                state = monthState,
                items = months,
                visibleItemsCount = 5,
                modifier = Modifier.width(monthWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = colors.BlueGrey30,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient
            )
        }

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.Transparent, RoundedCornerShape(12.dp))
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(itemHeight)
                    .width(dayWidth)
                    .background(Color.Transparent, RoundedCornerShape(12.dp))
            )
            Picker(
                state = dayState,
                items = days,
                visibleItemsCount = 5,
                modifier = Modifier.width(dayWidth),
                textStyle = textStyle,
                selectedTextStyle = selectedTextStyle,
                dividerColor = colors.BlueGrey30,
                itemPadding = itemPadding,
                fadingEdgeGradient = fadingEdgeGradient
            )
        }
    }
}

@Composable
fun calculateWidth(items: List<String>, textStyle: TextStyle): Dp {
    val measurer = rememberTextMeasurer()
    val maxText = items.maxByOrNull { it.length } ?: ""
    val result = measurer.measure(AnnotatedString(maxText), style = textStyle)
    return with(LocalDensity.current) {
        result.size.width.toDp() + 20.dp
    }
}