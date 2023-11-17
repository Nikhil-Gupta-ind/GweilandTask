package com.nikhil.gweilandtask.di

import com.nikhil.gweilandtask.api.CoinAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        return Retrofit.Builder().baseUrl("https://pro-api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesCoinAPI(retrofit: Retrofit) : CoinAPI {
        return retrofit.create(CoinAPI::class.java)
    }
}