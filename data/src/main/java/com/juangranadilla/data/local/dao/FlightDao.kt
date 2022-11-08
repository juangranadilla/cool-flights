package com.juangranadilla.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.juangranadilla.data.local.entity.FlightEntity

@Dao
interface FlightDao {

    @Query("SELECT * FROM flights WHERE creationDay = :day")
    suspend fun getFlightsByDay(day: String): List<FlightEntity>

    @Query("SELECT EXISTS(SELECT * FROM flights WHERE id = :id)")
    suspend fun checkIfFlightExists(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlight(flights: FlightEntity)
}