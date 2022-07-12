package com.sean.oxford.scribdproject.screens.weather

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.screens.base.BaseFragment
import com.sean.oxford.scribdproject.screens.base.BaseFragmentView
import com.sean.oxford.scribdproject.screens.base.ViewState
import com.sean.oxford.scribdproject.screens.weather.widgets.WeatherToolBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class WeatherFragment(private val viewModelFactory: ViewModelProvider.Factory) : BaseFragment() {

    private val viewModel: WeatherViewModel by viewModels { viewModelFactory }

    override fun getToolbarMenu(): Int = R.menu.menu_weather

    override fun initBaseView(): BaseFragmentView<ViewState> =
        WeatherFragmentView(viewModel, requireContext()) as BaseFragmentView<ViewState>

    override fun getToolbarView(): View? = WeatherToolBar(requireContext())
}