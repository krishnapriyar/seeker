package com.seeker.seeker.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(@ApplicationContext context: Context){
    private val sharedPref by lazy {
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    companion object {
        const val KEY_JWT_TOKEN = "KEY_JWT_TOKEN"
    }

    fun saveLoginToken(token: String?) {
        sharedPref.edit().putString(KEY_JWT_TOKEN, token).apply()
    }

    fun getLoginToken(): String? {
        return sharedPref.getString(KEY_JWT_TOKEN, null)
    }


}