package com.sean.oxford.scribdproject.di

import android.app.Application
import com.sean.oxford.scribdproject.activity.AppNavHostFragment
import com.sean.oxford.scribdproject.di.module.*
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton


@InternalCoroutinesApi
@Singleton
@Component(
    modules = [ViewModelModule::class,
        FragmentFactoryModule::class,
        RepositoryModule::class,
        PersistenceModule::class,
        NetworkModule::class]
)
interface ScribdComponent {

    fun inject(navHostFragment: AppNavHostFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ScribdComponent
    }


}