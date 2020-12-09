package com.deonolarewaju.mycurrencyconverter.ui.listeners

interface DataStateListener {

    fun onSuccess()

    fun onError(message: String)

    fun displayMessage(message: String)

    fun onLoading()
}