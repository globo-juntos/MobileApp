package com.wilderpereira.multiplatform.globojuntos.extensions

import android.view.View

fun View.toggleVisibility() {
    if (this.visibility == View.VISIBLE) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}