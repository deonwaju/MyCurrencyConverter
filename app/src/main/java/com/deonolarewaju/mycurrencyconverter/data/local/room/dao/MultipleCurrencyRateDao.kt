package com.deonolarewaju.mycurrencyconverter.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse

@Dao
interface MultipleCurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(currencyRates: CurrencyRates)

    @Query("SELECT * FROM fixer_response ORDER BY id DESC")
    suspend fun getRecordsFrom30Days(): List<FixerResponse>

    @Query("DELETE FROM fixer_response")
    suspend fun clearRecords()

}