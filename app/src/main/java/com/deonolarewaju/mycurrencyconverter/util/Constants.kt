package com.deonolarewaju.mycurrencyconverter.util

object Constants {

    object Database {
        const val DATABASE_NAME = "currency_calculator_db"
    }

    object Remote {
        const val BASE_URL = "http://data.fixer.io/"
    }

    object Nations {
        const val AUD = "AUD"
        const val CAD = "CAD"
        const val MXN = "MXN"
        const val NGN = "NGN"
        const val PLN = "PLN"
        const val USD = "USD"
    }

    object RateRecords {
        const val SINGLE_MONTH = 30
        const val THREE_MONTHS = 90
    }

}