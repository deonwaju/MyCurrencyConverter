package com.deonolarewaju.mycurrencyconverter.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.CurrencyRates
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.deonolarewaju.mycurrencyconverter.repository.MainRepository
import com.deonolarewaju.mycurrencyconverter.ui.listeners.DataStateListener
import com.deonolarewaju.mycurrencyconverter.util.CouroutinesHelper
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class MainActivityViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    val SINGLE_MONTH = 30
    private var mutableCurrencyRates: MutableLiveData<CurrencyRates> = MutableLiveData()
    private var mutableCurrentCurrencyRates: MutableLiveData<List<FixerResponse>> =
        MutableLiveData()

    val liveCurrentRate: LiveData<CurrencyRates>
        get() = mutableCurrencyRates

    val liveCurrencyDatesDay: LiveData<List<FixerResponse>>
        get() = mutableCurrentCurrencyRates


    fun viewModelInit(dataStateListener: DataStateListener) {
        CouroutinesHelper.io {
            mutableCurrencyRates.postValue(
                mainRepository.getStoredCurrencyRatesData(
                    dataStateListener
                )
            )
            mutableCurrentCurrencyRates.postValue(
                mainRepository.fetchSaveAndRetrieveRecordsForSomeDays(
                    SINGLE_MONTH,
                    dataStateListener
                )
            )
        }
    }

    fun convertAmount(
        countries: List<Double>,
        fromCurrencyPosition: Int,
        toCurrencyPosition: Int,
        amount: Double
    ): Double {
        val exchangeRates = getExchangeRates(countries, fromCurrencyPosition)
        val newExchangeRates = exchangeRates * countries[toCurrencyPosition]
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.roundingMode = RoundingMode.FLOOR

        return decimalFormat.format(amount * newExchangeRates).toDouble()
    }

    private fun getExchangeRates(
        countries: List<Double>,
        fromCurrencyPosition: Int
    ): Double {
        return 1 / countries[fromCurrencyPosition]
    }


}