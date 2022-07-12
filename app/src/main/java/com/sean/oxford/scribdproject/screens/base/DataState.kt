package com.sean.oxford.scribdproject.screens.base

import com.sean.oxford.scribdproject.network.MessageModel
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
data class DataState<T>(
    val messageModel: MessageModel? = null,
    val data: T? = null,
    val stateEvent: StateEvent? = null,
    val returnAction: ReturnAction? = null
){
    fun isJobCompleted(): Boolean = stateEvent != null
}