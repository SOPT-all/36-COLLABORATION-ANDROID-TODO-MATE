package com.example.myapplication.data.local.datasourceimpl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.myapplication.data.local.datasource.DummyLocalDataSource

class DummyLocalDataSourceImpl(context: Context) : DummyLocalDataSource {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    override var nickname: String
        get() = sharedPreferences.getString(NICKNAME, INITIAL_VALUE).toString()
        set(value) {
            sharedPreferences.edit().putString(NICKNAME, value).apply()
        }

    override fun clear() = sharedPreferences.edit { clear() }

    companion object {
        private const val PREFERENCES_NAME = "user_preferences"
        private const val NICKNAME = "nickname"
        private const val INITIAL_VALUE = ""
    }
}
