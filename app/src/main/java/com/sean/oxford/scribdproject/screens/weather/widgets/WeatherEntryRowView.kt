package com.sean.oxford.scribdproject.screens.weather.widgets
import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.TextView
import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.getString
import com.sean.oxford.scribdproject.kelvinToFahrenheit
import com.sean.oxford.scribdproject.model.WeatherEntry
import java.text.SimpleDateFormat
import java.util.*

class WeatherEntryRowView(context: Context, val thing: DoThinger) : FrameLayout(context) {

    interface DoThinger{
        fun thing()

    }


    private val temp: TextView
    private val humid: TextView
    private val date: TextView
    private val time: TextView
    private val weather: TextView

    init {
        layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        inflate(context, R.layout.rowview_weather_entry, this)
        temp = findViewById(R.id.TextView_weather_row_temp)
        humid = findViewById(R.id.TextView_weather_row_humid)
        date = findViewById(R.id.TextView_weather_row_date)
        weather = findViewById(R.id.TextView_weather_row_weather)
        time = findViewById(R.id.TextView_weather_row_time)
    }

    fun setWeatherEntry(weatherEntry: WeatherEntry){
        val dgFahrenheit = kelvinToFahrenheit(weatherEntry.main.temp)
        temp.text = getString(R.string.weather_entry_format_temp, dgFahrenheit)

        val humidity = weatherEntry.main.humidity
        humid.text = getString(R.string.weather_entry_format_humid, humidity)

        val timeParsed = Date(weatherEntry.dt.toLong() * 1000)

        val dateFormat = SimpleDateFormat("EE, M/d, yyyy")
        val dateString = dateFormat.format(timeParsed)
        date.text = dateString

        val timeFormat = SimpleDateFormat("h:mm a")
        val timeString = timeFormat.format(timeParsed)
        time.text = timeString

        var weatherThings = ""
        for(weather in weatherEntry.weather){
            val weatherDesc = weather.description
            if(weatherThings.isEmpty()){
                weatherThings += weatherDesc.substring(0, 1).toUpperCase() + weatherDesc.substring(1)
            } else {
                weatherThings += ", " + weather.description
            }
        }
        weather.text = weatherThings
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        thing.thing()
    }




}