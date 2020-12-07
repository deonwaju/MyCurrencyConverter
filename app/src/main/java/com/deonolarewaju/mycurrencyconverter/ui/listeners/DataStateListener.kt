package com.deonolarewaju.mycurrencyconverter.ui.listeners

interface DataStateListener {

    fun onSuccess()

    fun onError(message: String)

    fun onCurrentState(message: String)

    fun onLoading()
}