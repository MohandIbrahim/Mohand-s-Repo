package com.test.bostatask.data.local

import android.content.Context
import androidx.preference.PreferenceManager
import com.test.agent.common.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class PreferencesHelper @Inject constructor (  @ApplicationContext context: Context?) {
    private val appContext = context?.applicationContext
//    fun saveUserToken(token: String){
//        appContext?.let {
//            PreferenceManager.getDefaultSharedPreferences(it).edit()
//                .putString(Constants.Preferences.TOKEN, token).apply()
//        }
//    }
//    fun getUserToken(): String? = appContext?.let {
//        PreferenceManager.getDefaultSharedPreferences(it).getString(Constants.Preferences.TOKEN, "")
//    }


}