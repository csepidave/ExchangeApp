package exchangerates.com.csepi.exchangerates.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import exchangerates.com.csepi.exchangerates.BuildConfig
import exchangerates.com.csepi.exchangerates.BuildConfig.BASE_URL
import exchangerates.com.csepi.exchangerates.data.model.Rates
import exchangerates.com.csepi.exchangerates.data.network.DataManager
import exchangerates.com.csepi.exchangerates.data.parser.RatesParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 10L

@Module
class AppModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.BASIC
        return loggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        val gson = GsonBuilder()
                .registerTypeAdapter(Rates::class.java, RatesParser())
                .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideContentManager(retrofit: Retrofit): DataManager {
        return DataManager(retrofit)
    }
}