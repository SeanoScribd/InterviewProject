package com.sean.oxford.scribdproject.screens.weather

import com.sean.oxford.scribdproject.repository.AppRepository
import com.sean.oxford.scribdproject.screens.base.BaseViewModel
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.base.StateEvent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@InternalCoroutinesApi
class WeatherViewModel
@Inject
constructor(private val appRepository: AppRepository) : BaseViewModel<WeatherViewState>() {

    override fun getStateEventFlow(event: StateEvent): Flow<DataState<WeatherViewState>> {
        return when (event){
            is WeatherStateEvent.FetchWeatherStateEvent -> appRepository.fetchWeather(event)
            else -> flow{
            }
        }
    }

    override fun initViewState(): WeatherViewState = WeatherViewState(listOf(), "")
}