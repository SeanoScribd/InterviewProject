package com.sean.oxford.scribdproject.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sean.oxford.scribdproject.model.*
import java.lang.reflect.Type

class Converters {

    @TypeConverter
    fun fromStringToWeatherEntries(value: String): List<WeatherEntry> {
        val weatherEntryListType: Type = object : TypeToken<List<WeatherEntry>>() {}.type
        return Gson().fromJson(value, weatherEntryListType)
    }

    @TypeConverter
    fun fromWeatherEntriesToString(list: List<WeatherEntry>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToWeatherEntry(value: String): WeatherEntry {
        val weatherEntryType: Type = object : TypeToken<WeatherEntry>() {}.type
        return Gson().fromJson(value, weatherEntryType)
    }

    @TypeConverter
    fun fromWeatherEntryToString(weatherEntry: WeatherEntry): String {
        val gson = Gson()
        return gson.toJson(weatherEntry)
    }

    @TypeConverter
    fun fromStringToMainAttrs(value: String): MainAttrs {
        val mainAttrsType: Type = object : TypeToken<MainAttrs>() {}.type
        return Gson().fromJson(value, mainAttrsType)
    }

    @TypeConverter
    fun fromMainAttrsToString(mainAttrs: MainAttrs): String {
        val gson = Gson()
        return gson.toJson(mainAttrs)
    }

    @TypeConverter
    fun fromStringToWeathers(value: String): List<Weather> {
        val weathersListType: Type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(value, weathersListType)
    }

    @TypeConverter
    fun fromWeathersToString(list: List<Weather>): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringToWeather(value: String): Weather {
        val weatherType: Type = object : TypeToken<Weather>() {}.type
        return Gson().fromJson(value, weatherType)
    }

    @TypeConverter
    fun fromWeatherToString(weather: Weather): String {
        val gson = Gson()
        return gson.toJson(weather)
    }

    @TypeConverter
    fun fromStringToClouds(value: String): Clouds {
        val cloudsType: Type = object : TypeToken<Clouds>() {}.type
        return Gson().fromJson(value, cloudsType)
    }

    @TypeConverter
    fun fromCloudsToString(clouds: Clouds): String {
        val gson = Gson()
        return gson.toJson(clouds)
    }

    @TypeConverter
    fun fromStringToRain(value: String): Rain? {
        val rainType: Type = object : TypeToken<Rain>() {}.type
        return Gson().fromJson(value, rainType)
    }

    @TypeConverter
    fun fromCloudsToString(rain: Rain?): String {
        val gson = Gson()
        return gson.toJson(rain)
    }


}