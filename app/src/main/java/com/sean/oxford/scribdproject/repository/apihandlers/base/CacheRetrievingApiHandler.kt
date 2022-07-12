package com.sean.oxford.scribdproject.repository.apihandlers.base

import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.getString
import com.sean.oxford.scribdproject.network.ApiResult
import com.sean.oxford.scribdproject.network.MessageModel
import com.sean.oxford.scribdproject.network.MessageType.*
import com.sean.oxford.scribdproject.network.UIComponentType.Dialog
import com.sean.oxford.scribdproject.persistence.CacheResult
import com.sean.oxford.scribdproject.persistence.CacheResult.Success
import com.sean.oxford.scribdproject.repository.buildError
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.base.StateEvent
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow

@InternalCoroutinesApi
abstract class CacheRetrievingApiHandler<NetworkObject, CacheObject, ViewState> constructor(
    private val stateEvent: StateEvent,
    private val cacheCall: (suspend () -> CacheObject),
    apiCall: suspend () -> NetworkObject
) : CachingApiHandler<NetworkObject, CacheObject, ViewState>(stateEvent, apiCall) {

    override val result = flow {
        emit(returnCache(false))
        val apiResult = safeApiCall()
        emit(resolveApiResult(apiResult))
    }

    private suspend fun returnCache(markJobComplete: Boolean): DataState<ViewState> {
        val cacheResult = safeCacheCall(cacheCall)
        var jobCompleteMarker: StateEvent? = null
        if (markJobComplete) {
            jobCompleteMarker = stateEvent
        }

        return when (cacheResult) {
            is Success -> createSuccessViewStateFromCache(cacheResult.data, jobCompleteMarker)
            is CacheResult.Error -> buildError(cacheResult.error, Dialog(), stateEvent)
        }
    }

    override suspend fun createSuccessViewState(
        data: NetworkObject,
        stateEvent: StateEvent?
    ): DataState<ViewState> = returnCache(true)


    abstract fun createSuccessViewStateFromCache(
        data: CacheObject,
        stateEvent: StateEvent?
    ): DataState<ViewState>

    private suspend fun <T> safeCacheCall(cacheCall: suspend () -> T): CacheResult<T> =
        try {
            Success(cacheCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> CacheResult.Error(getString(R.string.dialog_message_error_cache_timeout_error))
                else -> CacheResult.Error(throwable.message)
            }
        }


}