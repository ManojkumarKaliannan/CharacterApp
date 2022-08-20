package com.owow.characterapp.utills

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import org.koin.core.component.KoinComponent

class SharedPreference(androidApplication: Application) : KoinComponent {
    private val preferenceName = "preferenceDTT"
    private var shared: SharedPreferences
    private var editor: SharedPreferences.Editor? = null

    init {
        this.shared = androidApplication.getSharedPreferences(
            preferenceName,
            Context.MODE_PRIVATE
        )
    }

    fun getBooleanValue(key: String): Boolean {
        return shared.getBoolean(key, false)
    }

    fun setBooleanValue(key: String, value: Boolean) {
        try {
            editor = shared.edit()
            editor!!.putBoolean(key, value)
            editor!!.apply()
        } catch (e: Exception) {
        }
    }

}