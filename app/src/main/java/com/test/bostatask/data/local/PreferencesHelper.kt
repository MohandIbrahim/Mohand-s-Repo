package com.test.bostatask.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class PreferencesHelper @Inject constructor (  @ApplicationContext context: Context?) {
    private val appContext = context?.applicationContext
}