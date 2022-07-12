package com.sean.oxford.scribdproject.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "weather_entry")
data class WeatherEntry(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Double,

    @ColumnInfo(name = "dt")
    val dt: Double,

    @ColumnInfo(name = "main")
    val main: MainAttrs,

    @ColumnInfo(name = "weather")
    val weather: List<Weather>,

    @ColumnInfo(name = "visibility")
    val visiblity: Int,

    @ColumnInfo(name = "clouds")
    val clouds: Clouds,

    @ColumnInfo(name = "rain")
    val rain: Rain?,

    @ColumnInfo(name = "city")
    var city: String



    ) : Parcelable