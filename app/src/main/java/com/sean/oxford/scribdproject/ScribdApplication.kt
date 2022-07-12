package com.sean.oxford.scribdproject

import android.app.Application
import com.sean.oxford.scribdproject.di.DaggerScribdComponent
import com.sean.oxford.scribdproject.di.ScribdComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
open class ScribdApplication: Application() {

    lateinit var appComponent: ScribdComponent

    companion object {
        lateinit var sApplication: ScribdApplication
    }

    override fun onCreate() {
        sApplication = this
        super.onCreate()
        initAppComponent()
    }

    protected open fun initAppComponent(){
        appComponent = DaggerScribdComponent.factory().create(this)
    }

}