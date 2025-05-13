package com.example.myapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Mono Tone
val White = Color(0xFFFFFFFF)
val Grey10 = Color(0xFFF2F2F2)
val Grey20 = Color(0xFFF2F3F5)
val Grey30 = Color(0xFFE9EEF0)
val Grey40 = Color(0xFFE9EEF2)
val Grey50 = Color(0xFFD2D8DA)
val Grey60 = Color(0xFFBCBCBC)
val Grey70 = Color(0xFF727272)

val BlueGrey10 = Color(0xFFCAD3DB)
val BlueGrey20 = Color(0xFFA1A8B3)
val BlueGrey30 = Color(0xFF959AA6)
val BlueGrey40 = Color(0xFF7E90AB)

val DarkGrey10 = Color(0xFF282828)
val DarkGrey20 = Color(0xFF0D0D0D)
val DarkGrey30 = Color(0xFF121212)
val Black = Color(0xFF000000)

// Category
val GreenCategory1 = Color(0xFFF5CD47)
val PurpleCategory2 = Color(0xFF797EF6)
val BlueCategory3 = Color(0xFF1AA7EC)

// Edit
val RedDelete = Color(0xFFFF5C5D)
val BlueEdit = Color(0xFF5483FF)
val YellowMemo = Color(0xFFFFD71C)
val PinkTime = Color(0xFFF465E7)
val PurpleMove = Color(0xFF696EF5)

// Others
val YellowSmile = Color(0xFFF9E314)
val RedHeart = Color(0xFFFE7168)
val Red10 = Color(0xFFFE0000)
val Blue10 = Color(0xFFDBF6FF)
val Blue20 = Color(0xFF007CFF)
val GradientAiStart = Color(0xFF38CBC9)
val GradientAiEnd = Color(0xFF3D6AFF)
val EmeraldAi = Color(0xFF38CBC9)
val GradientAi02Start = Color(0xFF0029F7)
val GradientAi02End = Color(0xFF00E550)

@Immutable
data class TodomateColors(
    // Mono Tone
    val White: Color,
    val Grey10: Color,
    val Grey20: Color,
    val Grey30: Color,
    val Grey40: Color,
    val Grey50: Color,
    val Grey60: Color,
    val Grey70: Color,

    val BlueGrey10: Color,
    val BlueGrey20: Color,
    val BlueGrey30: Color,
    val BlueGrey40: Color,

    val DarkGrey10: Color,
    val DarkGrey20: Color,
    val DarkGrey30: Color,
    val Black: Color,

    // Category
    val GreenCategory1: Color,
    val PurpleCategory2: Color,
    val BlueCategory3: Color,

    // Edit
    val RedDelete: Color,
    val BlueEdit: Color,
    val YellowMemo: Color,
    val PinkTime: Color,
    val PurpleMove: Color,

    // Others
    val YellowSmile: Color,
    val RedHeart: Color,
    val Red10: Color,
    val Blue10: Color,
    val Blue20: Color,
    val GradientAiStart: Color,
    val GradientAiEnd: Color,
    val EmeraldAi: Color,
    val GradientAi02Start: Color,
    val GradientAi02End: Color
)

val defaultTodomateColors = TodomateColors(
    White = White,
    Grey10 = Grey10,
    Grey20 = Grey20,
    Grey30 = Grey30,
    Grey40 = Grey40,
    Grey50 = Grey50,
    Grey60 = Grey60,
    Grey70 = Grey70,

    BlueGrey10 = BlueGrey10,
    BlueGrey20 = BlueGrey20,
    BlueGrey30 = BlueGrey30,
    BlueGrey40 = BlueGrey40,

    DarkGrey10 = DarkGrey10,
    DarkGrey20 = DarkGrey20,
    DarkGrey30 = DarkGrey30,
    Black = Black,

    GreenCategory1 = GreenCategory1,
    PurpleCategory2 = PurpleCategory2,
    BlueCategory3 = BlueCategory3,

    RedDelete = RedDelete,
    BlueEdit = BlueEdit,
    YellowMemo = YellowMemo,
    PinkTime = PinkTime,
    PurpleMove = PurpleMove,

    YellowSmile = YellowSmile,
    RedHeart = RedHeart,
    Red10 = Red10,
    Blue10 = Blue10,
    Blue20 = Blue20,
    GradientAiStart = GradientAiStart,
    GradientAiEnd = GradientAiEnd,
    EmeraldAi = EmeraldAi,
    GradientAi02Start = GradientAi02Start,
    GradientAi02End = GradientAi02End
)

val LocalTodomateColorsProvider = staticCompositionLocalOf { defaultTodomateColors }