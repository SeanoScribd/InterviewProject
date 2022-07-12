package com.sean.oxford.scribdproject.screens.base

import com.sean.oxford.scribdproject.R
import com.sean.oxford.scribdproject.getString
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
abstract class StateEvent{

    var showLoading: Boolean = false

    open fun errorString(): String = getString(R.string.dialog_message_error_default)



}
