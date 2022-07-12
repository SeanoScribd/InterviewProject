package com.sean.oxford.scribdproject.di.module

import com.sean.oxford.scribdproject.network.ApiService
import com.sean.oxford.scribdproject.persistence.AppDao
import com.sean.oxford.scribdproject.repository.AppRepository
import com.sean.oxford.scribdproject.repository.AppRepositoryImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@InternalCoroutinesApi
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideProdRepository(apiService: ApiService, appDao: AppDao): AppRepository =
        AppRepositoryImpl(apiService, appDao)


}