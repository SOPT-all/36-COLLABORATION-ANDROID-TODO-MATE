package com.example.myapplication.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.LocalTodomateColorsProvider
import com.example.myapplication.ui.theme.LocalTodomateTypographyProvider

@Composable
fun RepeatCycleSelector(
    selected: RepeatCycle,
    onSelect: (RepeatCycle) -> Unit,
    onBackClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_moveleft),
                contentDescription = "이전"
            )

            Text(
                text = "반복",
                style = LocalTodomateTypographyProvider.current.body_reg_14
            )

            Text(
                text = "완료",
                modifier = Modifier.clickable { onConfirmClick() },
                style = LocalTodomateTypographyProvider.current.cap_med_12
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            RepeatCycle.values().forEach { cycle ->
                val isSelected = selected == cycle

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 3.dp)
                        .background(color = LocalTodomateColorsProvider.current.Grey20)
                        .clickable { onSelect(cycle) }
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isSelected) R.drawable.icon_selection_true else R.drawable.icon_selection_false
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Unspecified
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = cycle.displayName,
                        style = LocalTodomateTypographyProvider.current.cap_reg_12
                    )
                }
            }
        }
    }
}

enum class RepeatCycle(val displayName: String) {
    DAILY("매일"),
    WEEKLY("매주"),
    BIWEEKLY("격주"),
    MONTHLY("매월"),
    YEARLY("매년")
}

@Preview(showBackground = true)
@Composable
fun PreviewRepeatCycleSelector() {
    var selected by remember { mutableStateOf(RepeatCycle.DAILY) }

    RepeatCycleSelector(
        selected = selected,
        onSelect = { selected = it },
        onBackClick = { /* 뒤로 가기 */ },
        onConfirmClick = { /* 완료 */ }
    )
}

