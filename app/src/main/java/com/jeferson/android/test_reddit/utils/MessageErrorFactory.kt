package com.jeferson.android.test_reddit.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.jeferson.android.test_reddit.R

class MessageErrorFactory {
    companion object {
        const val GENERIC_ERROR = 0
        const val NETWORK_ERROR = 1
    }

    fun showSnackBar(context: Context, type: Int?, view: View) {
        when (type) {
            NETWORK_ERROR -> {
                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_network_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
            else -> {
                Log.e("ERROR FACTORY", "Se debe agregar el mensaje de error para el código $type")

                val snackBar = Snackbar.make(
                    view,
                    context.getString(R.string.text_generic_error),
                    Snackbar.LENGTH_LONG
                )

                snackBar.view.setBackgroundColor(Color.parseColor("#fc645c"))
                snackBar.show()
            }
        }
    }
}