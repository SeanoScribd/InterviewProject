package com.sean.oxford.scribdproject.screens.weather

import com.sean.oxford.scribdproject.screens.base.StateEvent
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
sealed class WeatherStateEvent : StateEvent() {

    class FetchWeatherStateEvent(val city: String, val days: Int): WeatherStateEvent()

}