package com.deonolarewaju.mycurrencyconverter.data.model

import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.google.gson.annotations.SerializedName

data class FixerResponseModel(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rates")
    val rates: CurrencyRates,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timeStamp: Int
)