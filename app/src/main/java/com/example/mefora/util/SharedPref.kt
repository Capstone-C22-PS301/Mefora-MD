package com.example.mefora.util

import android.content.Context

class SharedPref(private val context: Context) {
    companion object {
        const val PREF_NAME = "mefora"
        const val PREF_USER_ID = "user_id"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val prefsEditor = prefs.edit()!!

    fun setUserId(id: String) {
        prefsEditor.putString(PREF_USER_ID, id)
    }

    fun getUserId(): String {
        return prefs.getString(PREF_USER_ID, "0").toString()
    }

}