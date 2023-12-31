package com.nikhil.gweilandtask.repository

import android.util.Log
import com.google.gson.JsonObject
import com.nikhil.gweilandtask.api.CoinAPI
import com.nikhil.gweilandtask.model.CoinsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CoinRepository @Inject constructor(private val coinAPI: CoinAPI) {

    private val _coins = MutableStateFlow<List<CoinsResponse.Data>>(emptyList())
    val coins: StateFlow<List<CoinsResponse.Data>>
        get() = _coins

    suspend fun getTop20Coins(sort: String) {
        val response = coinAPI.getCoins(sort)
        if (response.isSuccessful && response.body() != null) {
            Log.d("Response", "getCoins: ${response.body()}")
            val list = response.body()!!.data.take(20)
            _coins.emit(list)
            getLogos(list)
        }
    }

    private suspend fun getLogos(list: List<CoinsResponse.Data>) {
        val ids = mutableListOf<Int>()
        list.forEach {
            ids.add(it.id)
        }
        val response = coinAPI.getCurrencyInfo(ids.joinToString(separator = ","))
        if (response.isSuccessful && response.body() != null) {
            val json: JsonObject? = response.body()
            val dataObject = org.json.JSONObject(json.toString()).getJSONObject("data")
            var i = 0
            dataObject.keys().forEach { key ->
                val itemObject = dataObject.getJSONObject(key)
//                if(list[i].id == itemObject.getInt("id")) {
//                    list[i++].logoUrl = itemObject.getString("logo")
//                }
                list.forEach {
                    if(it.id == itemObject.getInt("id")) {
                        it.logoUrl = itemObject.getString("logo")
                    }
                }
                i++
            }

            val newList = list.subList(0, list.size - 1) // for recomposition
            _coins.emit(newList)
        }
    }
}