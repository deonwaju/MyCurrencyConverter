package com.deonolarewaju.mycurrencyconverter.repository

import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.CurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.MultipleCurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.deonolarewaju.mycurrencyconverter.data.model.FixerResponseModel
import com.deonolarewaju.mycurrencyconverter.data.remote.retrofit.RetrofitApi
import com.deonolarewaju.mycurrencyconverter.ui.DataStateListener
import com.deonolarewaju.mycurrencyconverter.util.ApiRequestHelper
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val currencyRateDao: CurrencyRateDao,
    private val multipleCurrencyRateDao: MultipleCurrencyRateDao
): ApiRequestHelper() {

    private val TAG = "MainRepository"
    private lateinit var calendar: Calendar

    private suspend fun getCurrentRates(): FixerResponseModel{
        return apiRequest{ retrofitApi.getLatestExchangeRates()}
    }

    suspend fun getStoredCurrencyRatesData(dataStateListener: DataStateListener): FixerResponseModel {

    }
}