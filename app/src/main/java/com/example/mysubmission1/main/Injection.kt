package com.example.mysubmission1.main

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mysubmission1.main.API.ApiConfig
import com.example.mysubmission1.main.ModelFactory.Companion.getInstance
import com.example.mysubmission1.main.UserPreferences.Companion.getInstance
import com.example.mysubmission1.main.model.DataSource


private val Context.database: DataStore<Preferences> by preferencesDataStore("token")

object Injection {
    fun provideRepository(context: Context): DataSource {
        val preferences = UserPreferences.getInstance(context.database)
        return DataSource.getInstance(preferences)
    }
}