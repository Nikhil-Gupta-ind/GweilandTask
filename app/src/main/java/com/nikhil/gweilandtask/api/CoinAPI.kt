package com.nikhil.gweilandtask.api

import com.google.gson.JsonObject
import com.nikhil.gweilandtask.BuildConfig
import com.nikhil.gweilandtask.model.CoinsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinAPI {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCoins(@Query("sort") sort: String): Response<CoinsResponse>

    @GET("v2/cryptocurrency/info")
    suspend fun getCurrencyInfo(@Query("id") id: String): Response<JsonObject>
}