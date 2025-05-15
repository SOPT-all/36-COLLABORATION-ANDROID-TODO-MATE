package com.example.myapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

@Immutable
data class TodomateTypography(
    val body_med_16: TextStyle,
    val body_semi_14: TextStyle,
    val body_reg_14: TextStyle,
    val cap_bold_12: TextStyle,
    val cap_semi_12: TextStyle,
    val cap_med_12: TextStyle,
    val cap_reg_12: TextStyle,
    val cap_semi_10: TextStyle,
    val cap_med_10: TextStyle,
    val cap_reg_10: TextStyle,
    val cap_bold_8: TextStyle
)

val defaultTodomateTypography = TodomateTypography(
    body_med_16 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    body_semi_14 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    body_reg_14 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    cap_bold_12 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    cap_semi_12 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp
    ),
    cap_med_12 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    cap_reg_12 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    cap_semi_10 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp
    ),
    cap_med_10 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp
    ),
    cap_reg_10 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    cap_bold_8 = TextStyle(
        fontFamily = Pretendard,
        fontWeight = FontWeight.Bold,
        fontSize = 8.sp
    )
)

val LocalTodomateTypographyProvider = staticCompositionLocalOf { defaultTodomateTypography }