package com.mobly.app.data.preference

interface SharedPref {

    fun getApiKeyToken(): String
    fun setApiKeyToken(token: String)

    fun clearAll()
}