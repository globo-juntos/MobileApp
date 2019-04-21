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
        val option3: String? = null,
        val shareInfo: ShareInfo? = null
) : Parcelable


@Parcelize
data class ShareInfo(val title: String, val subtitle: String, val shareText: String) : Parcelable