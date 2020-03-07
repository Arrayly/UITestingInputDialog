package com.example.uitestinginputdialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var nameTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById(R.id.MainActivity_tv)
    }

    fun showDialog(view: View) {
        showDialog()
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)

        val confirmBtn = dialog.findViewById(R.id.inputDialog_Confirm_Btn) as Button
        val cancelBtn = dialog.findViewById(R.id.inputDialog_Cancel_Btn) as Button
        val editText = dialog.findViewById(R.id.inputDialog_EditText) as EditText

        confirmBtn.setOnClickListener {
            nameTextView.text = editText.text.toString()
            dialog.dismiss()
        }

        cancelBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }
}
