package com.sean.oxford.scribdproject.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sean.oxford.scribdproject.network.MessageModel
import com.sean.oxford.scribdproject.network.MessageType
import com.sean.oxford.scribdproject.network.UIComponentType


class MessageQueue : ArrayList<MessageModel>() {

    private val _stateMessage: MutableLiveData<MessageModel> = MutableLiveData()


    val stateMessage: LiveData<MessageModel>
        get() = _stateMessage


    override fun add(element: MessageModel): Boolean {
        if (this.contains(element)) {
            return false
        }
        val transaction = super.add(element)
        if (this.size == 1) {
            setStateMessage(element)
        }
        return transaction
    }

    override fun removeAt(index: Int): MessageModel {

        try {
            val transaction = super.removeAt(index)
            if (this.size > 0) {
                setStateMessage(this[0])
            } else {
                setStateMessage(null)
            }
            return transaction
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        }
        return MessageModel( "", UIComponentType.None(), MessageType.None())
    }


    private fun setStateMessage(stateMessage: MessageModel?) {
        _stateMessage.value = stateMessage
    }

}