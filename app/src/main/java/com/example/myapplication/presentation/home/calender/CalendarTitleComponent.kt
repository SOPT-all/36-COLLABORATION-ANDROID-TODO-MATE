package com.example.myapplication.presentation.home.calender

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.DarkGrey10
import com.example.myapplication.ui.theme.Grey10
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarTitleComponent(
    yearText: Int,
    monthText: Int,
    totalDoneTaskNum: Int = 0,
    totalEmojiNum: Int = 0,
    totalHeartNum: Int = 0,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit
) {
    val typography = LocalTodomateTypographyProvider.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.year_and_month, yearText, monthText),
            style = typography.body_semi_14,
            color = Black
        )

        Spacer(modifier = Modifier.width(8.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            IconAndTextComponent(
                imageId = R.drawable.icon_check,
                contentDescriptionId = R.string.icon_check,
                text = totalDoneTaskNum.toString()
            )
            IconAndTextComponent(
                imageId = R.drawable.icon_smile,
                contentDescriptionId = R.string.icon_smile,
                text = totalEmojiNum.toString()
            )
            IconAndTextComponent(
                imageId = R.drawable.icon_heart,
                contentDescriptionId = R.string.icon_heart,
                text = totalHeartNum.toString()
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = onLeftClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_moveleft),
                contentDescription = stringResource(R.string.ic_moveleft)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        IconButton(
            onClick = onRightClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_moveright),
                contentDescription = stringResource(R.string.ic_moveright)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Box(
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(width = 34.dp, height = 24.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Grey10)
            )
            Text(
                text = stringResource(R.string.week),
                style = typography.cap_bold_12,
                color = DarkGrey10
            )
        }
    }
}

@Composable
fun IconAndTextComponent(
    imageId: Int,
    contentDescriptionId: Int,
    text: String
) {
    val typography = LocalTodomateTypographyProvider.current

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(contentDescriptionId),
            modifier = Modifier.size(12.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            style = typography.body_semi_14,
            color = Black
        )
    }
}

@Preview
@Composable
private fun PreviewCalendarTitleComponent() {
    CalendarTitleComponent(
        yearText = 2025,
        monthText = 4,
        onLeftClick = {},
        onRightClick = {}
    )
}