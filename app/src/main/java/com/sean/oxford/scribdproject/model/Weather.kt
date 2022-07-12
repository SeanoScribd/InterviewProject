package com.sean.oxford.scribdproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(val id: Int, val description: String) : Parcelable