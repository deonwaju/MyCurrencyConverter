package com.deonolarewaju.mycurrencyconverter.ui.presenation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.deonolarewaju.mycurrencyconverter.R
import com.deonolarewaju.mycurrencyconverter.ui.listeners.DataStateListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener,
    DataStateListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onCurrentState(message: String) {
        TODO("Not yet implemented")
    }

    override fun onLoading() {
        TODO("Not yet implemented")
    }
}