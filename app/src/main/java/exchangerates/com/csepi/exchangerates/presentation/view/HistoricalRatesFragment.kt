package exchangerates.com.csepi.exchangerates.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import exchangerates.com.csepi.exchangerates.R

class HistoricalRatesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_historical_rates, container, false)

    companion object {
        fun newInstance(): HistoricalRatesFragment = HistoricalRatesFragment()
    }
}