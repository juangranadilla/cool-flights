package com.juangranadilla.presentation.ui.viewmodel

import androidx.lifecycle.*
import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import com.juangranadilla.domain.usecase.GetCoolFlightsUseCase
import kotlinx.coroutines.launch

class CoolFlightsViewModel(
    private val getCoolFlightsUseCase: GetCoolFlightsUseCase
) : ViewModel() {

    lateinit var coolFlightsLiveData: LiveData<DataState<List<Flight>>>

    init {
        viewModelScope.launch {
            coolFlightsLiveData = getCoolFlightsUseCase().asLiveData()
        }
    }
}