package exchangerates.com.csepi.exchangerates.di

import dagger.Component
import exchangerates.com.csepi.exchangerates.data.network.DataManager
import exchangerates.com.csepi.exchangerates.presentation.presenter.ActualRatesPresenter

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getContentManager() : DataManager
    fun inject(actualRatesPresenter: ActualRatesPresenter)
}