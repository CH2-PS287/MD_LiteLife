package com.example.litelife.data.di

import android.content.Context
import com.example.litelife.data.pref.UserPreference
import com.example.litelife.data.pref.dataStore
import com.example.litelife.data.repo.UserRepository


object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}