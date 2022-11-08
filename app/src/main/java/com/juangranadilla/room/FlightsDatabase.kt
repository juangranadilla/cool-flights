package com.juangranadilla.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juangranadilla.data.local.dao.FlightDao
import com.juangranadilla.data.local.entity.FlightEntity

private const val FLIGHTS_DATABASE_VERSION = 1

@Database(entities = [FlightEntity::class], version = FLIGHTS_DATABASE_VERSION)
abstract class FlightsDatabase : RoomDatabase() {
    abstract fun flightsDao(): FlightDao
}