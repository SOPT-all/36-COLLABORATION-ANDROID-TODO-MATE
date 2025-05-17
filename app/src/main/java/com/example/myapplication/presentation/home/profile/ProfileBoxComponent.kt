package com.example.myapplication.presentation.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.theme.DarkGrey20
import com.example.myapplication.ui.theme.Grey50
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider

@Composable
fun ProfileBoxComponent(
    imageId: Int,
    nameId: Int
) {
    val typography = LocalTodomateTypographyProvider.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(R.string.icon_profile),
            modifier = Modifier
                .size(52.dp)
                .border(width = 0.5.dp, color = Grey50, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = stringResource(nameId),
            style = typography.cap_med_12,
            color = DarkGrey20
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.icon_aiplus),
            contentDescription = stringResource(R.string.icon_aiplus),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewProfileBoxComponent() {
    ProfileBoxComponent(
        imageId = R.drawable.icon_me,
        nameId = R.string.profile_name
    )
}