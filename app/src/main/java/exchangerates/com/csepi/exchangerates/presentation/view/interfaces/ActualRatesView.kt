package exchangerates.com.csepi.exchangerates.presentation.view.interfaces

import exchangerates.com.csepi.exchangerates.data.model.Rate

interface ActualRatesView {
    fun stopLoading()
    fun showExchangeRates(rates: List<Rate>)
    fun showErrorSnackbar(errorMessage: String)
    fun showError(throwable: Throwable)
}