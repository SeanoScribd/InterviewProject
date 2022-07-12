package com.sean.oxford.scribdproject

import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.roundToInt


@OptIn(InternalCoroutinesApi::class)
fun getString(id: Int): String {
    return ScribdApplication.sApplication.getString(id)
}


@OptIn(InternalCoroutinesApi::class)
fun getString(id: Int, extra: Any): String {
    return ScribdApplication.sApplication.getString(id, extra)
}


fun kelvinToFahrenheit(kelvin: Float): Int {
    //(300.75K − 273.15) × 9/5 + 32
    return ((kelvin - 273.15) * 1.8 + 32).roundToInt()

}