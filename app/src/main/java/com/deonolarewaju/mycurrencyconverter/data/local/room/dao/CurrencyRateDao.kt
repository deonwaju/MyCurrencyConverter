package com.deonolarewaju.mycurrencyconverter.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates


@Dao
interface CurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(currencyRates: CurrencyRates)

    @Query("SELECT * FROM currency_rates")
    suspend fun getCurrencyRates(): CurrencyRates

    @Query("DELETE FROM currency_rates")
    suspend fun clearRates()

}