package exchangerates.com.csepi.exchangerates.data.network

import exchangerates.com.csepi.exchangerates.data.model.ApiResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FixerApiService {

    @GET("latest")
    fun getActualRates(
            @Query("access_key") apiKey: String,
            @Query("base") baseCurrency: String,
            @Query("symbols") currencyCodes: List<String>
    ) : Single<ApiResponseModel>

    @GET
    fun getHistoricalRates(
            @Url url: String,
            @Query("access_key") apiKey: String,
            @Query("base") baseCurrency: String,
            @Query("symbols") currencyCodes: List<String>
    ) : Single<ApiResponseModel>

}