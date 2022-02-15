package com.deonolarewaju.mycurrencyconverter.ui.presentation

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.observe
import com.deonolarewaju.mycurrencyconverter.R
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.deonolarewaju.mycurrencyconverter.data.model.CurrencyRatesModel
import com.deonolarewaju.mycurrencyconverter.ui.adapters.SpinnerAdapter
import com.deonolarewaju.mycurrencyconverter.ui.graph.LineGraphMarker
import com.deonolarewaju.mycurrencyconverter.ui.listeners.DataStateListener
import com.deonolarewaju.mycurrencyconverter.ui.viewModel.MainActivityViewModel
import com.deonolarewaju.mycurrencyconverter.util.Constants
import com.deonolarewaju.mycurrencyconverter.util.Constants.toast
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener,
    DataStateListener {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private lateinit var countries: MutableList<CurrencyRatesModel>
    private var convertFromPosition = 0
    private var convertToPosition = 0
    private var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        progressDialog = ProgressDialog(this)
        mainActivityViewModel.viewModelInit(this)

        setUpLineChart()

        mainActivityViewModel.liveCurrentRate.observe(this) { rates ->
            if (rates != null) {

                countries = mutableListOf()
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.AUD,
                        rates.aUD,
                        R.drawable.aud_flag
                    )
                )
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.NGN,
                        rates.nGN,
                        R.drawable.ngn_flag
                    )
                )
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.CAD,
                        rates.cAD,
                        R.drawable.cad_flag
                    )
                )
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.MXN,
                        rates.mXN,
                        R.drawable.mxn_flag
                    )
                )
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.PLN,
                        rates.pLN,
                        R.drawable.pln_flag
                    )
                )
                countries.add(
                    CurrencyRatesModel(
                        Constants.CountriesList.USD,
                        rates.uSD,
                        R.drawable.usd_flag
                    )
                )
                populateSpinnerFrom(countries)
                populateSpinnerTo(countries)

            }
        }

        mainActivityViewModel.liveCurrencyDatesDay.observe(this) {
            onLoading()
            if (it != null) {
                displayMessage("loading data on graph...")
                initLineChart(it)

            }
            onSuccess()
        }
        convert_to_edit_text.isEnabled = false

        convert_btn.setOnClickListener(this)

    }

    private fun populateSpinnerTo(countries: MutableList<CurrencyRatesModel>) {
        convert_to_spinner.adapter = SpinnerAdapter(this, countries)

        convert_to_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                rootView: View?,
                position: Int,
                p3: Long
            ) {
                convert_to_text.text = countries[position].name
                convertToPosition = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun populateSpinnerFrom(countries: MutableList<CurrencyRatesModel>) {
        convert_from_spinner.adapter = SpinnerAdapter(this, countries)

        convert_from_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                rootView: View?,
                position: Int,
                p3: Long
            ) {
                convert_from_text.text = countries[position].name
                convertFromPosition = position
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    private fun setUpLineChart() {
        currency_chart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(true)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        currency_chart.axisLeft.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }

        currency_chart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }


        currency_chart.apply {
            this.setDrawBorders(false)
            setDrawGridBackground(false)
            setNoDataTextColor(Color.WHITE)
            description.isEnabled = true
            description.textColor = Color.WHITE
            description.text = "Naira against Exchange Rates with Euros"

            legend.isEnabled = true
            legend.textColor = Color.WHITE
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.convert_btn) {
            if (convert_from_edit_text.text.toString().isEmpty()) {
                toast("Input desired currency amount")
                return
            }
            val result = mainActivityViewModel.convertAmount(
                countries.map { it.rate },
                convertFromPosition,
                convertToPosition,
                convert_from_edit_text.text.toString().toDouble()
            )
            convert_to_edit_text.setText(result.toString())
        }
    }

    override fun onSuccess() {
        progressDialog?.dismiss()
    }

    override fun onError(message: String) {
        progressDialog?.dismiss()
        toast(message)
    }

    override fun displayMessage(message: String) {
        progressDialog?.setMessage(message)
    }

    override fun onLoading() {
        progressDialog?.setCancelable(false)
        progressDialog?.setMessage("Loading exchange rate data...")
        progressDialog?.show()
    }

    private fun initLineChart(conversionRates: List<FixerResponse>) {

        conversionRates.let { rates ->

            val lineDataSet =
                LineDataSet(
                    rates.indices.map { i ->
                        Entry(i.toFloat(), rates[i].rates?.nGN?.toFloat()!!)
                    },
                    "Exchange Rates"
                ).apply {
                    valueTextColor = Color.WHITE
                    color = ContextCompat.getColor(this@MainActivity, R.color.skyBlue)
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                    setDrawFilled(true)
                    fillAlpha = 255
                }
            currency_chart.data = LineData(lineDataSet).also { it.setDrawValues(false) }
            currency_chart.marker =
                LineGraphMarker(this, conversionRates.toMutableList(), R.layout.marker_main)

            currency_chart.animation = AnimationUtils.loadAnimation(this, R.anim.fade_anim)
            currency_chart.invalidate()


        }
    }


}