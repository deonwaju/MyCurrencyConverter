package com.deonolarewaju.mycurrencyconverter.di

import android.content.Context
import androidx.room.Room
import com.deonolarewaju.mycurrencyconverter.data.local.room.db.AppDatabase
import com.deonolarewaju.mycurrencyconverter.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RoomDbModule {

//    @Provides
//    @Singleton
//    fun providesDatabaseInstance(@ApplicationContext context: Context) =
//        synchronized(context){
//            Room.databaseBuilder(
//                context,
//                AppDatabase::class.java,
//               Constants.DataBase.DATABASE_NAME
//            ).build()
//        }
//
//    @Provides
//    @Singleton
//    fun providesCurrencyRateDao(appDatabase: AppDatabase) = appDatabase.getCurrencyRateDao()
//
//
//    @Provides
//    @Singleton
//    fun providesMultipleCurrencyRateDao(appDatabase: AppDatabase) = appDatabase.getMultipleCurrencyRatesDao()
}