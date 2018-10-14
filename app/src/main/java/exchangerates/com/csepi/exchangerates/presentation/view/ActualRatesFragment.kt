package exchangerates.com.csepi.exchangerates.presentation.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import exchangerates.com.csepi.exchangerates.R
import exchangerates.com.csepi.exchangerates.data.model.Rate
import exchangerates.com.csepi.exchangerates.presentation.presenter.ActualRatesPresenter
import exchangerates.com.csepi.exchangerates.presentation.view.adapter.ActualRatesAdapter
import exchangerates.com.csepi.exchangerates.presentation.view.interfaces.ActualRatesView
import exchangerates.com.csepi.exchangerates.utils.showErrorSnackbarWithAction
import kotlinx.android.synthetic.main.fragment_actual_rates.*
import java.util.*

const val defaultCurrency = "EUR"

class ActualRatesFragment : Fragment(), ActualRatesView {

    private val actualRatesPresenter by lazy { ActualRatesPresenter(this) }
    private val actualRatesAdapter by lazy { ActualRatesAdapter() }
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_actual_rates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        clearAdapter()
        linearLayoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = actualRatesAdapter
        val locale = context?.resources?.configuration?.locale
        val baseCurrency = Currency.getInstance(locale).currencyCode
        loadData(baseCurrency)
    }

    private fun clearAdapter() {
        actualRatesAdapter.setData(null)
    }

    private fun loadData(baseCurrency: String) {
        progressBar.visibility = VISIBLE
        actualRatesPresenter.getActualRates(baseCurrency)
    }

    override fun showExchangeRates(rates: List<Rate>) {
        actualRatesAdapter.setData(rates)
    }

    private fun showSnackbar(message: String) {
        val messageWithAdditionalInfo = "\n" + resources.getString(R.string.try_again_with_default_base_currency, message)
        root.showErrorSnackbarWithAction(messageWithAdditionalInfo, R.string.retry,
                View.OnClickListener {
                    loadData(defaultCurrency)
                })
    }

    override fun showErrorSnackbar(errorMessage: String) {
        clearAdapter()
        val message = if (errorMessage.isNotEmpty()) {
            errorMessage
        } else {
            resources.getString(R.string.default_error_message)
        }
        showSnackbar(message)
    }

    override fun showError(throwable: Throwable) {
        clearAdapter()
        val message = throwable.message ?:
        resources.getString(R.string.default_error_message)
        showSnackbar(message)
    }

    override fun stopLoading() {
        progressBar.visibility = GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        actualRatesPresenter.clear()
        clearAdapter()
    }

    companion object {
        fun newInstance(): ActualRatesFragment = ActualRatesFragment()
    }

}