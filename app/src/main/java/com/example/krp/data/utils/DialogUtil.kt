package com.example.krp.data.utils

import android.app.AlertDialog
import android.content.Context
import com.example.krp.R

class DialogUtil(private val context: Context) {

    fun showDialog(text: String) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(text)
        builder.setPositiveButton(R.string.ok) { dialog, which ->}
        builder.show()
    }
}