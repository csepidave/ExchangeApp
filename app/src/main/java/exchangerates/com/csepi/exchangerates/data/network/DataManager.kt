package exchangerates.com.csepi.exchangerates.data.network

import exchangerates.com.csepi.exchangerates.BuildConfig.API_KEY
import exchangerates.com.csepi.exchangerates.data.model.ApiResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class DataManager @Inject constructor(retrofit: Retrofit) {
    private val service = retrofit.create<FixerApiService>(FixerApiService::class.java)

    fun requestActualRates(baseCurrency: String, currencyCodes: List<String>) : Single<ApiResponseModel> {
        return service.getActualRates(API_KEY, baseCurrency, currencyCodes)
    }

    fun requestHistoricalRates(url: String, baseCurrency: String, currencyCodes: List<String>)
            : Single<ApiResponseModel> {
        return service.getHistoricalRates(url, API_KEY, baseCurrency, currencyCodes)
    }
}