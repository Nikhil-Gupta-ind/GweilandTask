package com.nikhil.gweilandtask.di

import com.nikhil.gweilandtask.BuildConfig
import com.nikhil.gweilandtask.api.CoinAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    // https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest
    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor = { chain ->
                val originalRequest = chain.request()
                val newRequest = originalRequest.newBuilder()
                    .header("X-CMC_PRO_API_KEY", BuildConfig.COINS_KEY)
                    .build()

                chain.proceed(newRequest)
            })
            .build()
        return Retrofit.Builder().baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesCoinAPI(retrofit: Retrofit) : CoinAPI {
        return retrofit.create(CoinAPI::class.java)
    }
}