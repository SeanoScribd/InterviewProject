package com.sean.oxford.scribdproject.repository

import android.content.Context
import android.net.ConnectivityManager
import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.ScribdApplication
import com.sean.oxford.scribdproject.getString
import com.sean.oxford.scribdproject.network.MessageModel
import com.sean.oxford.scribdproject.network.MessageType
import com.sean.oxford.scribdproject.network.UIComponentType
import com.sean.oxford.scribdproject.persistence.CacheResult
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.base.StateEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext





@InternalCoroutinesApi
fun <ViewState> buildError(
    message: String?,
    uiComponentType: UIComponentType,
    stateEvent: StateEvent?
): DataState<ViewState> =
    DataState(MessageModel(message, uiComponentType, MessageType.Error()), null, stateEvent, null)



@InternalCoroutinesApi
fun hasConnectivity(): Boolean {
    val connectivityManager =
        ScribdApplication.sApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}