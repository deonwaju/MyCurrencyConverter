package com.deonolarewaju.mycurrencyconverter.di

import android.content.Context
import androidx.room.Room
import com.deonolarewaju.mycurrencyconverter.data.local.room.db.AppDatabase
import com.deonolarewaju.mycurrencyconverter.util.Constants
import com.deonolarewaju.mycurrencyconverter.util.NetworkInterceptors
import com.facebook.stetho.okhttp3.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {




}