package com.sean.oxford.scribdproject.model.response

import android.os.Parcelable
import com.sean.oxford.scribdproject.model.City
import com.sean.oxford.scribdproject.model.WeatherEntry
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeatherResponse(
    val list: List<WeatherEntry>,
    val city: City,
    val code: Int,
    val message: String
) : Parcelable