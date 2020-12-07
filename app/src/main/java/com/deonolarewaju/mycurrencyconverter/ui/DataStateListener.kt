package com.deonolarewaju.mycurrencyconverter.ui

interface DataStateListener {

    fun onSuccess()

    fun onError(message: String)

    fun onCurrentState(message: String)

    fun onLoading()
}