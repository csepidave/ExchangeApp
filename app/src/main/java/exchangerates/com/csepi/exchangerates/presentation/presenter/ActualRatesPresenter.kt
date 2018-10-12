package exchangerates.com.csepi.exchangerates.presentation.presenter

import exchangerates.com.csepi.exchangerates.data.network.DataManager
import exchangerates.com.csepi.exchangerates.di.DaggerAppComponent
import exchangerates.com.csepi.exchangerates.presentation.view.ActualRatesFragment
import exchangerates.com.csepi.exchangerates.utils.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ActualRatesPresenter(private val view: ActualRatesFragment?) {

    init {
        DaggerAppComponent.create().inject(this)
    }

    @Inject
    lateinit var dataManager: DataManager

    private val disposables = CompositeDisposable()

    private fun isViewAttached() = view?.isAdded ?: false

    fun getActualRates() {
        if (isViewAttached()) {
            disposables += dataManager.requestActualRates("EUR", null)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doAfterTerminate { view?.stopLoading() }
                    .subscribe({
                        if (it.success) {
                            view?.showExchangeRates(it.rates?.getRates()!!)
                        } else {
                            view?.showErrorSnackbar()
                        }
                    }, {
                        view?.showError(it)
                    })
        }
    }

    fun clear() {
        disposables.clear()
    }
}