package com.sean.oxford.scribdproject.screens.base

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.sean.oxford.scribdproject.R

class LoadingView(context: Context): FrameLayout(context) {


    init {
        View.inflate(context, R.layout.view_loading, this)
        visibility = GONE
    }
}