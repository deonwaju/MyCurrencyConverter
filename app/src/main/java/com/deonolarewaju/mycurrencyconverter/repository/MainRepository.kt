package com.deonolarewaju.mycurrencyconverter.repository

import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.CurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.dao.MultipleCurrencyRateDao
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.deonolarewaju.mycurrencyconverter.data.model.FixerResponseModel
import com.deonolarewaju.mycurrencyconverter.data.remote.retrofit.RetrofitApi
import com.deonolarewaju.mycurrencyconverter.ui.listeners.DataStateListener
import com.deonolarewaju.mycurrencyconverter.util.ApiException
import com.deonolarewaju.mycurrencyconverter.data.remote.ApiRequestHelper
import com.deonolarewaju.mycurrencyconverter.util.CouroutinesHelper
import com.deonolarewaju.mycurrencyconverter.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitApi: RetrofitApi,
    private val currencyRateDao: CurrencyRateDao,
    private val multipleCurrencyRateDao: MultipleCurrencyRateDao
): ApiRequestHelper() {

    private lateinit var calendar: Calendar

    private suspend fun getCurrentRates(): FixerResponseModel {
        return apiRequest { retrofitApi.getLatestExchangeRates() }
    }

    suspend fun getStoredCurrencyRatesData(dataStateListener: DataStateListener): CurrencyRates {

        CouroutinesHelper.io {
            try {
                withContext(Dispatchers.Main) {
                    dataStateListener.onLoading()
                }
                currencyRateDao.clearRates()

                val currencyRates = getCurrentRates()
                withContext(Dispatchers.Main) {
                    dataStateListener.displayMessage("Saving Data..")
                }

                currencyRateDao.insertRates(currencyRates.rates)

                withContext(Dispatchers.Main) {
                    dataStateListener.onSuccess()
                }
            } catch (e: NoInternetException) {
                withContext(Dispatchers.Main) {
                    dataStateListener.onError(e.message!!)
                }
            } catch (e: ApiException) {
                withContext(Dispatchers.Main) {
                    dataStateListener.onError(e.message!!)
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    dataStateListener.onError(e.message!!)
                }
            }
        }

        return currencyRateDao.getCurrencyRates()
    }

    suspend fun fetchSaveAndRetrieveRecordsForSomeDays(
        days: Int,
        progressStateListener: DataStateListener
    ): List<FixerResponse> {
        withContext(Dispatchers.Main) {
            progressStateListener.onLoading()
        }
        multipleCurrencyRateDao.clearRecords()
        try {
            for (x in days downTo 1) {
                withContext(Dispatchers.Main) {
                    progressStateListener.displayMessage("Saving record for day $x")
                }
                val date = getDateFromNDaysAgo(x)

                multipleCurrencyRateDao.insertRates(
                    FixerResponse(
                        date = date,
                        rates = getCurrencyByDate(date).rates
                    )
                )
            }

            withContext(Dispatchers.Main) {
                progressStateListener.onSuccess()
            }
        } catch (e: NoInternetException) {
            withContext(Dispatchers.Main) {
                progressStateListener.onError(e.message!!)
            }
        } catch (e: ApiException) {
            withContext(Dispatchers.Main) {
                progressStateListener.onError(e.message!!)
            }
        } catch (e: IOException) {
            withContext(Dispatchers.Main) {
                progressStateListener.onError(e.message!!)
            }
        }
        return multipleCurrencyRateDao.getRecordsFrom30Days()
    }

    private suspend fun getCurrencyByDate(date: String): FixerResponseModel {
        return apiRequest { retrofitApi.getCurrencyByDate(date) }
    }

    private fun getDateFromNDaysAgo(daysAgo: Int): String {
        calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -daysAgo)
        val simpleDateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.getDefault())

        return simpleDateFormat.format(calendar.time)
    }


}