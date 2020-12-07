package com.deonolarewaju.mycurrencyconverter.util

import android.content.Context
import android.widget.Toast
import com.deonolarewaju.mycurrencyconverter.BuildConfig
import timber.log.Timber

object Constants {

    object DataBase {
        const val DATABASE_NAME = "currency_rates_db"
        const val VERSION_N0 = 1
    }

    object CountriesList {
        const val AUD = "AUD"
        const val CAD = "CAD"
        const val MXN = "MXN"
        const val USD = "USD"
        const val NGN = "NGN"
        const val PLN = "PLN"
    }

    object URL {
        const val BASE_URL = BuildConfig.BASE_URL
    }

    object Keys {
        const val API_KEY = BuildConfig.API_KEY
    }


    fun Context.toast(message: String) {
        val centerToast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        centerToast.show()
    }

    fun Context.timberLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return String.format(
                        "Class:%s: Line: %s, Method: %s",
                        super.createStackElementTag(element),
                        element.lineNumber,
                        element.methodName
                    )
                }
            })
        } else {
            Timber.plant(ReleaseTree())
        }

    }

}