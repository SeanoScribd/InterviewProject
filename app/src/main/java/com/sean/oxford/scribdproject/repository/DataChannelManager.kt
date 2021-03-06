package com.sean.oxford.scribdproject.repository

import androidx.lifecycle.MutableLiveData
import com.sean.oxford.scribdproject.screens.base.DataState
import com.sean.oxford.scribdproject.screens.base.ReturnAction
import com.sean.oxford.scribdproject.screens.base.StateEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@FlowPreview
abstract class DataChannelManager<ViewState> {

    private val _activeStateEvents: HashSet<String> = HashSet()
    private var ioScope: CoroutineScope? = null

    val messageQueue = MessageQueue()
    val numActiveJobs: MutableLiveData<Int> = MutableLiveData()

    fun launchJob(stateEvent: StateEvent, job: Flow<DataState<ViewState>>) {
        if (!isStateEventActive(stateEvent) && messageQueue.size == 0) {
            addStateEvent(stateEvent)
            job.onEach { dataState ->
                withContext(Dispatchers.Main) {
                    processDataState(dataState)
                }
            }.launchIn(getIOScope())
        }
    }

    private fun processDataState(dataState: DataState<ViewState>) {

        dataState.data?.let {
            handleViewState(it)
        }

        dataState.messageModel?.let {
            messageQueue.add(it)
        }

        if (dataState.isJobCompleted()) {
            removeStateEvent(dataState.stateEvent)
            dataState.returnAction?.let {
                handleReturnAction(it)
            }
        }
    }

    fun cancelJobs() {
        ioScope?.let {
            if (ioScope?.isActive == true) {
                ioScope?.cancel()
            }
        }
        ioScope = null
        clearActiveStateEventCounter()
    }

    private fun getIOScope(): CoroutineScope {
        if (ioScope == null) {
            ioScope = CoroutineScope(Dispatchers.IO)
        }
        return ioScope as CoroutineScope
    }

    private fun removeStateEvent(stateEvent: StateEvent?) {
        _activeStateEvents.remove(stateEvent?.javaClass?.simpleName)
        syncActiveJobCount()
    }

    private fun addStateEvent(stateEvent: StateEvent) {
        _activeStateEvents.add(stateEvent.javaClass.simpleName)
        syncActiveJobCount()
    }

    private fun isStateEventActive(stateEvent: StateEvent): Boolean {
        return _activeStateEvents.contains(stateEvent.toString())
    }

    fun clearStateMessage(index: Int = 0) {
        messageQueue.removeAt(index)
    }

    private fun clearActiveStateEventCounter() {
        _activeStateEvents.clear()
        syncActiveJobCount()

    }

    private fun syncActiveJobCount() {
        numActiveJobs.value = _activeStateEvents.size
    }

    abstract fun handleViewState(viewState: ViewState)

    abstract fun handleReturnAction(returnAction: ReturnAction?)

}