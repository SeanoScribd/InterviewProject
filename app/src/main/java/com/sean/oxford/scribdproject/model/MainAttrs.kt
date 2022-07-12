package com.sean.oxford.scribdproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainAttrs(
    val temp: Float,
    val feelsLike: Float,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Int,
    val humidity: Int
) : Parcelable