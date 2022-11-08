package com.juangranadilla.data.di

import com.juangranadilla.data.local.FlightsLocalDataSource
import com.juangranadilla.data.local.FlightsLocalDataSourceImpl
import com.juangranadilla.data.remote.FlightsService
import com.juangranadilla.data.remote.FlightsRemoteDataSource
import com.juangranadilla.data.remote.FlightsRemoteDataSourceImpl
import com.juangranadilla.data.repository.FlightsRepositoryImpl
import com.juangranadilla.domain.repository.FlightsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.skypicker.com/"

val dataModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(client = get()) }
    single { provideService(retrofit = get()) }
    single<FlightsRemoteDataSource> { FlightsRemoteDataSourceImpl(service = get()) }
    single<FlightsLocalDataSource> { FlightsLocalDataSourceImpl(database = get()) }
    single<FlightsRepository> { FlightsRepositoryImpl(remote = get(), local = get()) }
}

fun provideOkHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()

fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

fun provideService(retrofit: Retrofit): FlightsService =
    retrofit.create(FlightsService::class.java)