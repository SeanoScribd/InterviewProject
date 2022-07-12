package com.sean.oxford.scribdproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rain(@SerializedName("3h") val threeH: Float) : Parcelable