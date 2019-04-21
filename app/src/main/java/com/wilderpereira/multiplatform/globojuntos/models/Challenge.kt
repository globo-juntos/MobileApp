package com.wilderpereira.multiplatform.globojuntos.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Challenge(
        val id: String,
        val title: String,
        val description: String,
        val option1: String,
        val option2: String,
        var userInfoQuestion: Challenge? = null,
        var friendsCount: Int? = 0,
        val option3: String? = null
) : Parcelable