package com.nikhil.gweilandtask.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.gweilandtask.model.CoinsResponse
import com.nikhil.gweilandtask.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangesViewModel @Inject constructor(private val repository: CoinRepository): ViewModel() {

    val coins: StateFlow<List<CoinsResponse.Data>>
        get() = repository.coins
    private var selectedOption by mutableStateOf("market_cap")

    fun updateSelectedOption(selected: String){
        selectedOption = selected
        viewModelScope.launch {
            repository.getTop20Coins(selectedOption)
        }
    }

    init {
        viewModelScope.launch {
            repository.getTop20Coins(selectedOption)
        }
    }
}