package com.juangranadilla.presentation.ui.viewmodel

import com.juangranadilla.domain.model.Flight
import com.juangranadilla.domain.result.DataState
import com.juangranadilla.domain.usecase.GetCoolFlightsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CoolFlightsViewModelTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var useCase: GetCoolFlightsUseCase

    private lateinit var viewModel: CoolFlightsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.IO)
    }

    @Test
    fun `Check that viewModel calls use case to get flights successfully on init`() = runTest {
        val dataState = mock<Flow<DataState<List<Flight>>>>()
        whenever(useCase()).thenReturn(dataState)

        viewModel = CoolFlightsViewModel(useCase)

        verify(useCase).invoke()
        verifyNoMoreInteractions(useCase)
    }
}