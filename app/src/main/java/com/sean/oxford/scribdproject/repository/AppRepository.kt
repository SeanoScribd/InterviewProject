package com.sean.oxford.scribdproject.repository

import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.weather.WeatherStateEvent.FetchWeatherStateEvent
import com.sean.oxford.scribdproject.screens.weather.WeatherViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@InternalCoroutinesApi
interface AppRepository{
    fun fetchWeather(fetchWeatherStateEvent: FetchWeatherStateEvent): Flow<DataState<WeatherViewState>>
}