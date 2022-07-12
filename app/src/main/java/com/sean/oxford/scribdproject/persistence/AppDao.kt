package com.sean.oxford.scribdproject.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sean.oxford.scribdproject.model.WeatherEntry

@Dao
interface AppDao {

    @Query("SELECT * FROM weather_entry WHERE dt >= :start AND dt <= :end AND city == :city")
    suspend fun getWeatherEntriesByTimeWindow(city: String, start: Long, end: Long): List<WeatherEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherEntry(weatherEntry: WeatherEntry): Long

}