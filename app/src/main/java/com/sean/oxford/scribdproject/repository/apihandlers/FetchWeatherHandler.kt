package com.sean.oxford.scribdproject.repository.apihandlers

import com.sean.oxford.scribdproject.model.WeatherEntry
import com.sean.oxford.scribdproject.model.response.WeatherResponse
import com.sean.oxford.scribdproject.network.ApiService
import com.sean.oxford.scribdproject.persistence.AppDao
import com.sean.oxford.scribdproject.repository.apihandlers.base.CacheRetrievingApiHandler
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.base.StateEvent
import com.sean.oxford.scribdproject.screens.weather.WeatherStateEvent.FetchWeatherStateEvent
import com.sean.oxford.scribdproject.screens.weather.WeatherViewState
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

@InternalCoroutinesApi
class FetchWeatherHandler(
    private val stateEvent: FetchWeatherStateEvent,
    private val apiService: ApiService,
    private val appDao: AppDao
) : CacheRetrievingApiHandler<WeatherResponse, List<WeatherEntry>, WeatherViewState>(stateEvent,
    apiCall = { apiService.getWeather(stateEvent.city, "ddcc33d46b4b685268271b988390cd3d") },
    cacheCall = {
        val start = Calendar.getInstance().timeInMillis / 1000
        val end = start + 60 * 60 * 24 * stateEvent.days
        appDao.getWeatherEntriesByTimeWindow(stateEvent.city.toLowerCase(), start, end)
    }) {

    override suspend fun saveToCache(data: WeatherResponse) {
        for (weather in data.list) {
            val city = data.city.name.toLowerCase()
            val hash = (city.hashCode() * weather.dt)
            weather.id = hash
            weather.city = city
            appDao.insertWeatherEntry(weather)
        }
    }

    override fun createSuccessViewStateFromCache(
        data: List<WeatherEntry>,
        stateEvent: StateEvent?
    ): DataState<WeatherViewState> =
        DataState(null, WeatherViewState(data, this.stateEvent.city), stateEvent, null)


}