package com.wilderpereira.multiplatform.globojuntos.repository

import android.content.Context

private const val voteKey = "vote"

fun saveVote(context: Context) {
    val sharedPreferences = context.getSharedPreferences("key", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean(voteKey, true)
    editor.apply()
}



fun hasAlreadyVoted(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("key", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean(voteKey, false)
}