package com.deonolarewaju.mycurrencyconverter.util

import androidx.room.TypeConverter
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class CurrencyRateConverter {

    @TypeConverter
    fun convertRatesToJson(currencyRates: CurrencyRates?): String{
        return GsonBuilder().create().toJson(currencyRates)
    }

    @TypeConverter
    fun convertJsonToRates(currencyRateString: String?): CurrencyRates?{
        val type = object : TypeToken<CurrencyRates>() {}.type
        return Gson().fromJson<CurrencyRates>(currencyRateString, type)
    }
}