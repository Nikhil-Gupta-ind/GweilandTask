package com.nikhil.gweilandtask.viewmodel

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

    init {
        viewModelScope.launch {
            repository.getTop20Coins()
        }
    }

    // todo filter and search text
}