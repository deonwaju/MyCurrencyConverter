package com.deonolarewaju.mycurrencyconverter.di

import android.content.Context
import androidx.room.Room
import com.deonolarewaju.mycurrencyconverter.data.local.room.db.AppDatabase
import com.deonolarewaju.mycurrencyconverter.data.remote.retrofit.RetrofitApi
import com.deonolarewaju.mycurrencyconverter.util.Constants
import com.deonolarewaju.mycurrencyconverter.util.NetworkInterceptor
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

    @Provides
    @Singleton
    fun getRetrofitInstance(@ApplicationContext context: Context): Retrofit {
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(NetworkInterceptor(context))
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): RetrofitApi = retrofit.create(RetrofitApi::class.java)

    @Provides
    @Singleton
    fun getDatabaseInstance(@ApplicationContext context: Context) =
        synchronized(context) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                Constants.DataBase.DATABASE_NAME
            ).build()
        }

    @Provides
    @Singleton
    fun getConverterSingleDao(appDatabase: AppDatabase) = appDatabase.getCurrencyRateDao()


    @Provides
    @Singleton
    fun getConverterMultipleDao(appDatabase: AppDatabase) =
        appDatabase.getMultipleCurrencyRatesDao()


}