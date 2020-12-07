package com.deonolarewaju.mycurrencyconverter.util

import android.content.Context
import android.widget.Toast

object Constants {

    object DataBase {
        const val DATABASE_NAME = "currency_rates_db"
        const val VERSION_N0 = 1
    }

    object CountriesList {
        const val AUD = "AUD"
        const val CAD = "CAD"
        const val MXN = "MXN"
        const val NGN = "NGN"
        const val PLN = "PLN"
        const val USD = "USD"
    }

    object URL {
        const val BASE_URL = "http://data.fixer.io/"
    }

    object Keys {
        const val API_KEY = "de85f98c6c9b44bbd74f84350ddc468f"
    }


    fun Context.toast(message: String) {
        val centerToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        centerToast.show()
    }


}