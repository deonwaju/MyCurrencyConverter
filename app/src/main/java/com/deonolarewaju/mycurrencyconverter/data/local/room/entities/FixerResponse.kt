package com.deonolarewaju.mycurrencyconverter.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "fixer_response")
data class FixerResponse (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "base")
    @SerializedName("base")
    var base: String? = null,

    @ColumnInfo(name = "date")
    @SerializedName("date")
    var date: String? = null,

    @ColumnInfo(name = "rates")
    @SerializedName("rates")
    var rates: CurrencyRates? = null,

    @Ignore
    @SerializedName("success")
    val success: Boolean? = null

    )