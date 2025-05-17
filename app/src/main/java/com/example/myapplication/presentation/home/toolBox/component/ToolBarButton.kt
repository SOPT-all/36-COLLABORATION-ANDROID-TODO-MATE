package com.example.myapplication.presentation.home.toolBox.component

import android.R.style.Theme
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.presentation.util.compose.noRippleClickable
import com.example.myapplication.ui.theme.BlueGrey10
import com.example.myapplication.ui.theme.BlueGrey40
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.Pretendard
import com.example.myapplication.ui.theme.TodomateTypography
import com.example.myapplication.ui.theme.White

@Composable
fun ToolBarButton(
    type: ToolBarButtonType,
    status: ToolBarButtonStatus,
    onClickCallback: () -> Unit
) {
    var icon: Int
    var title: String

    var backgroundColor: androidx.compose.ui.graphics.Color
    var iconColor: androidx.compose.ui.graphics.Color
    var titleColor: androidx.compose.ui.graphics.Color

    when(type) {
        ToolBarButtonType.Details -> {
            icon = R.drawable.icon_write
            title = stringResource(R.string.toolbar_details)
        }
        ToolBarButtonType.Routine -> {
            icon = R.drawable.icon_routine
            title = stringResource(R.string.toolbar_routine)
        }
        ToolBarButtonType.Importance -> {
            icon = R.drawable.icon_importance
            title = stringResource(R.string.toolbar_importance)
        }
    }

    when(status) {
        ToolBarButtonStatus.ON -> {
            backgroundColor = BlueGrey40
            iconColor = White
            titleColor = White
        }
        ToolBarButtonStatus.OFF -> {
            backgroundColor = BlueGrey10
            iconColor = BlueGrey40
            titleColor = BlueGrey40
        }
        ToolBarButtonStatus.UNAVAILABLE -> {
            backgroundColor = BlueGrey10
            iconColor = White
            titleColor = White
        }
    }


    Row(
        modifier = Modifier
            .wrapContentWidth()
            .height(32.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(25.dp))
            .noRippleClickable {
                onClickCallback.invoke()
            }
            .padding(vertical = 7.dp, horizontal = 12.dp),
        verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = title,
            tint = iconColor,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = title,
            color = titleColor,
            style = LocalTodomateTypographyProvider.current.cap_bold_12
        )
    }

}

enum class ToolBarButtonStatus {
    // 누를 수 있는 상태
    ON,
    // 눌러진 상태
    OFF,
    // 누를 수 없는 상태
    UNAVAILABLE
}

enum class ToolBarButtonType {
    Details,
    Routine,
    Importance
}