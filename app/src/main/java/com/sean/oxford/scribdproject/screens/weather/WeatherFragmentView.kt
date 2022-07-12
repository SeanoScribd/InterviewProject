package com.sean.oxford.scribdproject.screens.weather

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.screens.base.BaseFragmentView
import com.sean.oxford.scribdproject.screens.weather.WeatherStateEvent.FetchWeatherStateEvent
import com.sean.oxford.scribdproject.screens.weather.widgets.WeatherEntryAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class WeatherFragmentView(viewModel: WeatherViewModel, context: Context) :
    BaseFragmentView<WeatherViewState>(viewModel, context), WeatherEntryAdapter.Callback {

    private lateinit var recyclerView: RecyclerView


    private lateinit var adapter: WeatherEntryAdapter

    override fun onViewStateChanged(viewState: WeatherViewState) {
        adapter.submitList(viewState.weatherEntries)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_weather

    override fun initViews(menu: Menu?) {
        swipeRefreshLayout = findViewById(R.id.SwipeToRefreshLayout_weather)
        swipeRefreshLayout?.setOnRefreshListener {

        }
        recyclerView = findViewById(R.id.RecyclerView_weather_entries)


        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = WeatherEntryAdapter(this)
        recyclerView.adapter = adapter

    }

    override fun initTitle(): String = "Weather"

    override fun initToolbarView(toolbarView: View?) {
        val searchButton =
            toolbarView?.findViewById<ImageView>(R.id.ImageView_weather_toolbar_search)
        val cityEditText =
            toolbarView?.findViewById<EditText>(R.id.EditText_weather_toolbar)

        searchButton?.setOnClickListener {
            setStateEvent(FetchWeatherStateEvent(cityEditText?.text.toString(), 5))
        }

        cityEditText?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setStateEvent(FetchWeatherStateEvent(cityEditText.text.toString(), 5))
            }
            true
        }
    }

    override fun onClick() {
    }


}