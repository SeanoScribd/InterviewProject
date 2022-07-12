package com.sean.oxford.scribdproject.activity

import android.content.Context
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.sean.oxford.scribdproject.ScribdApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class AppNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var mainFragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        (activity?.application as ScribdApplication).appComponent.inject(this)
        childFragmentManager.fragmentFactory = mainFragmentFactory
        super.onAttach(context)
    }

}