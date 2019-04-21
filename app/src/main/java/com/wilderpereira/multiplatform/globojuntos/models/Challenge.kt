package com.wilderpereira.multiplatform.globojuntos.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Challenge(
        val id: String,
        val title: String,
        val description: String,
        val status: String,
        val option1: String,
        val option2: String,
        var userInfoQuestion: Challenge? = null,
        var friendsCount: Int? = -1,
        val option3: String? = null,
        val shareInfo: ShareInfo? = null,
        val image: Bitmap? = null,
        val time: Int = 0,
        val color: Int = 0
) : Parcelable


@Parcelize
data class ShareInfo(val title: String, val subtitle: String, val shareText: String) : Parcelable