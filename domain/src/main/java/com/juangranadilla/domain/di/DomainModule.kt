package com.juangranadilla.domain.di

import com.juangranadilla.domain.usecase.GetCoolFlightsUseCase
import com.juangranadilla.domain.usecase.GetCoolFlightsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetCoolFlightsUseCase> { GetCoolFlightsUseCaseImpl(repository = get()) }
}