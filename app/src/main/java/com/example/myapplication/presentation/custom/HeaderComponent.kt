package com.example.myapplication.presentation.custom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

@Composable
fun HeaderComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_logo),
            contentDescription = stringResource(R.string.icon_logo),
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_databox),
            contentDescription = stringResource(R.string.icon_databox),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_dm),
            contentDescription = stringResource(R.string.icon_dm),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.icon_menu),
            contentDescription = stringResource(R.string.icon_menu),
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewHeaderComponent() {
    HeaderComponent()
}