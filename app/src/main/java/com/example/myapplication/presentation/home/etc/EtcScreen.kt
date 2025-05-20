package com.example.myapplication.presentation.home.etc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Blue10
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider


@Composable
fun EtcScreen() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(color = Blue10, RoundedCornerShape(30.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.icon_ai),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )

            Spacer(Modifier.width(2.dp))

            Text(
                text = "AI 할 일 생성",
                style = LocalTodomateTypographyProvider.current.cap_med_12,
                color = Black
            )
        }

        Spacer(Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_list),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(10.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = "리스트 메뉴",
            style = LocalTodomateTypographyProvider.current.cap_med_12,
            color = Black
        )
    }
}