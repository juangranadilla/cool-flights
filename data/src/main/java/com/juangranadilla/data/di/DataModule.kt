package com.juangranadilla.data.di

import com.juangranadilla.data.remote.ApiService
import com.juangranadilla.data.remote.RemoteDataSource
import com.juangranadilla.data.remote.RemoteDataSourceImpl
import com.juangranadilla.data.repository.RepositoryImpl
import com.juangranadilla.domain.repository.Repository
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
    single<RemoteDataSource> { RemoteDataSourceImpl(service = get()) }
    single<Repository> { RepositoryImpl(remote = get()) }
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

fun provideService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)