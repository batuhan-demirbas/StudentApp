package com.batuhandemirbas.studentapp.util.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, duration).show()
}