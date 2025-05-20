package com.example.myapplication.presentation.home.importance

import com.example.myapplication.R

enum class ImportanceCycle(val displayName: String, val rightIconResId: Int) {
    UPPER("상", R.drawable.ic_importance_upper),
    MIDDLE("중", R.drawable.ic_importance_middle),
    LOWER("하", R.drawable.ic_importance_lower)
}