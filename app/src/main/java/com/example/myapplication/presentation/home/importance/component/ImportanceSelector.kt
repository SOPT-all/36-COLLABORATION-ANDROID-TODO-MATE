package com.example.myapplication.presentation.home.importance.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.myapplication.R
import com.example.myapplication.presentation.home.importance.ImportanceCycle
import com.example.myapplication.ui.theme.LocalTodomateColorsProvider
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider

@Composable
fun ImportanceSelector(
    selected: ImportanceCycle?,
    onSelect: (ImportanceCycle) -> Unit,
    onLeftClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    val typography = LocalTodomateTypographyProvider.current
    val colors = LocalTodomateColorsProvider.current

    val isConfirmEnabled = selected != null
    val confirmColor = if (isConfirmEnabled) colors.DarkGrey10 else colors.BlueGrey40

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
            IconButton(onClick = onLeftClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_moveleft),
                    contentDescription = "이전"
                )
            }

            Text(
                text = "중요도",
                style = typography.body_reg_14
            )

            Text(
                text = "완료",
                modifier = Modifier
                    .clickable(
                        enabled = isConfirmEnabled,
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { onConfirmClick() }
                    .padding(horizontal = 12.dp),
                style = typography.cap_med_12,
                color = confirmColor
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ImportanceCycle.entries.forEachIndexed { index, cycle ->
                val isSelected = selected == cycle
                val shape: Shape = when (index) {
                    0 -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                    ImportanceCycle.entries.size - 1 -> RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    else -> RoundedCornerShape(0.dp)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 3.dp)
                        .background(color = colors.Grey20, shape = shape)
                        .clickable { onSelect(cycle) }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = if (isSelected) R.drawable.icon_selection_true else R.drawable.icon_selection_false
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.Unspecified
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = cycle.displayName,
                            style = typography.cap_reg_12
                        )
                    }

                    Icon(
                        imageVector = ImageVector.vectorResource(id = cycle.rightIconResId),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

