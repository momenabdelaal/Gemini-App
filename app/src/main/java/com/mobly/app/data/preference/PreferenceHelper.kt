package com.mobly.app.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.mobly.app.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class PreferenceHelper @Inject constructor(@ApplicationContext context: Context) :
    SharedPref {
    private val masterKeyAlias: String = createGetMasterKey()

    private val sharedPreference: SharedPreferences = EncryptedSharedPreferences.create(
        context.getString(R.string.app_name),
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    private var editor: SharedPreferences.Editor = sharedPreference.edit()

    private fun createGetMasterKey(): String {
        return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    override fun getApiKeyToken(): String {
        return sharedPreference.getString("userApikeyToken","") ?: ""

    }

    override fun setApiKeyToken(token: String) {
        editor.putString("userApikeyToken", token).apply()

    }

    override fun clearAll() {
        editor.clear()

    }
}