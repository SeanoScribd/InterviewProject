package com.sean.oxford.scribdproject.screens.weather

import com.sean.oxford.scribdproject.model.WeatherEntry
import com.sean.oxford.scribdproject.screens.base.ViewState

data class WeatherViewState(val weatherEntries: List<WeatherEntry>, val searchTerm: String): ViewState()