package com.hk.iwanttobesupermvp.util

import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

// 코드리뷰 X
inline fun <reified T : Any> Context.getIntent() = Intent(this, T::class.java)
