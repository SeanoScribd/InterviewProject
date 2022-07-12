package com.sean.oxford.scribdproject.repository

import com.sean.oxford.scribdproject.network.ApiService
import com.sean.oxford.scribdproject.persistence.AppDao
import com.sean.oxford.scribdproject.repository.apihandlers.FetchWeatherHandler
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.weather.WeatherStateEvent.FetchWeatherStateEvent
import com.sean.oxford.scribdproject.screens.weather.WeatherViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@InternalCoroutinesApi
@Singleton
class AppRepositoryImpl
@Inject
constructor(private val apiService: ApiService, private val appDao: AppDao) : AppRepository {

    override fun fetchWeather(stateEvent: FetchWeatherStateEvent): Flow<DataState<WeatherViewState>> =
        FetchWeatherHandler(stateEvent, apiService, appDao).result

}