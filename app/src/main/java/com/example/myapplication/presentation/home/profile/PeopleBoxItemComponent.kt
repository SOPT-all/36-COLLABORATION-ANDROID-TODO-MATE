package com.example.myapplication.presentation.home.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
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
fun PeopleBoxItemComponent(
    imageId: Int,
    nameId: Int?,
) {
    val typography = LocalTodomateTypographyProvider.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(imageId),
            contentDescription = stringResource(R.string.icon_profile),
            modifier = Modifier
                .size(48.dp)
                .border(width = 0.5.dp, color = Grey50, shape = CircleShape)
        )
        Spacer(modifier = Modifier.height(6.dp))
        if (nameId != null) {
            Text(
                text = stringResource(nameId),
                style = typography.cap_bold_8,
                color = DarkGrey20
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPeopleBoxComponent() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // (1) text가 있는 경우
        PeopleBoxItemComponent(
            imageId = R.drawable.icon_me,
            nameId = R.string.profile_name
        )
        // (2) text가 없는 경우
        PeopleBoxItemComponent(
            imageId = R.drawable.icon_more,
            nameId = null
        )
    }
}