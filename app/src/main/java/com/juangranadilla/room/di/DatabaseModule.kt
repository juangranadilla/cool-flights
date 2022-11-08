package com.juangranadilla.room.di

import androidx.room.Room
import com.juangranadilla.data.local.DatabaseBridge
import com.juangranadilla.room.DatabaseBridgeImpl
import com.juangranadilla.room.FlightsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val FLIGHT_DATABASE_NAME = "flights_database"

val databaseModule = module {
    single<DatabaseBridge> { DatabaseBridgeImpl(database = get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            FlightsDatabase::class.java,
            FLIGHT_DATABASE_NAME
        ).build()
    }
}