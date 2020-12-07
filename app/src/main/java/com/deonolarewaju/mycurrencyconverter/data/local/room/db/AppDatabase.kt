package com.deonolarewaju.mycurrencyconverter.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.CurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.MultipleCurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.deonolarewaju.mycurrencyconverter.util.Constants
import com.deonolarewaju.mycurrencyconverter.util.CurrencyRateConverter


@Database(entities = [CurrencyRates::class, FixerResponse::class], version = Constants.DataBase.VERSION_N0)
@TypeConverters(CurrencyRateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getCurrencyRateDao(): CurrencyRateDao
    abstract fun getMultipleCurrencyRatesDao(): MultipleCurrencyRateDao
}