package com.example.myapplication.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Black
import com.example.myapplication.ui.theme.Grey70
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider
import com.example.myapplication.ui.theme.White

@Composable
fun MainBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .background(White)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            MainBottomBarItem(BottomBarType.Feed)
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            MainBottomBarItem(BottomBarType.Explore)
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            MainBottomBarItem(BottomBarType.Noti)
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            MainBottomBarItem(BottomBarType.My)
        }
    }
}

@Composable
fun MainBottomBarItem(bottomBarType: BottomBarType) {
    val typoProvider = LocalTodomateTypographyProvider.current

    var icon = 0
    var title = ""
    var titleColor = Black

    when (bottomBarType) {
        BottomBarType.Feed -> {
            icon = R.drawable.icon_feed
            title = stringResource(R.string.bottombar_feed)
            titleColor = Black
        }
        BottomBarType.Explore -> {
            icon = R.drawable.icon_explore
            title = stringResource(R.string.bottombar_explore)
            titleColor = Grey70
        }
        BottomBarType.Noti -> {
            icon = R.drawable.icon_noti
            title = stringResource(R.string.bottombar_noti)
            titleColor = Grey70
        }
        BottomBarType.My -> {
            icon = R.drawable.icon_my
            title = stringResource(R.string.bottombar_my)
            titleColor = Grey70
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = title,
            style = typoProvider.cap_med_10,
            color = titleColor
        )
    }
}

enum class BottomBarType {
    Feed, Explore, Noti, My
}