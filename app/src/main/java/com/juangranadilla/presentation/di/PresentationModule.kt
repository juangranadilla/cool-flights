package com.juangranadilla.presentation.di

import com.juangranadilla.presentation.ui.viewmodel.CoolFlightsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { CoolFlightsViewModel(getCoolFlightsUseCase = get()) }
}