package com.example.handlingbackenderrors.presentation.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog (val str: String): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(str)
                .setNegativeButton("Cancel") { dialog, id ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
}