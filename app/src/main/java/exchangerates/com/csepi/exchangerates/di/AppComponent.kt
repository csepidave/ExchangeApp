package exchangerates.com.csepi.exchangerates.di

import dagger.Component
import exchangerates.com.csepi.exchangerates.data.network.DataManager

@Component(modules = [AppModule::class])
interface AppComponent {
    fun getContentManager() : DataManager
}