package com.sean.oxford.scribdproject.screens.weather.widgets

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.sean.oxford.scribdproject.R

class WeatherToolBar(context: Context): FrameLayout(context) {

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        inflate(context, R.layout.toolbar_weather, this)
        isClickable = true
    }


}