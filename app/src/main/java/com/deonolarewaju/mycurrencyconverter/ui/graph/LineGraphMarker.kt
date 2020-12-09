package com.deonolarewaju.mycurrencyconverter.ui.graph

import android.content.Context
import com.deonolarewaju.mycurrencyconverter.data.local.room.entities.FixerResponse
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.marker_main.view.*
import java.math.RoundingMode
import java.text.DecimalFormat

class LineGraphMarker(
    context: Context,
    private var fixerResponse: MutableList<FixerResponse>,
    layoutId: Int
) : MarkerView(context, layoutId) {


    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)

        if (e == null) {
            return
        }

        val currentPosition = e.x.toInt()
        val currentRates = fixerResponse[currentPosition]

        marker_date.text = currentRates.date?.substring(5)
        val decimalFormat = DecimalFormat("#.##")
        decimalFormat.roundingMode = RoundingMode.FLOOR
        val exchangeText =
            "1 EUR -> ${decimalFormat.format(currentRates.rates?.nGN).toDouble()} NGN"
        marker_exchange_rate.text = exchangeText

    }

    override fun getOffset(): MPPointF {
        return MPPointF(02f, -height.toFloat())
    }

}