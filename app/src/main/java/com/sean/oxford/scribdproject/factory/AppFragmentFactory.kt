package com.sean.oxford.scribdproject.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.oxford.scribdproject.screens.weather.WeatherFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class AppFragmentFactory
@Inject
constructor(private val viewModelFactory: ViewModelProvider.Factory) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        when (className) {
            WeatherFragment::class.java.name -> return WeatherFragment(viewModelFactory)
        }
        return super.instantiate(classLoader, className)
    }
}