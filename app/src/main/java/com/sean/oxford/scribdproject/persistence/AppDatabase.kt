package com.sean.oxford.scribdproject.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sean.oxford.scribdproject.model.WeatherEntry
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
@Database(
    entities = [WeatherEntry::class],
    version = 23
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAppDao(): AppDao

    companion object {
        const val DATABASE_NAME: String = "app_db"
    }
}
