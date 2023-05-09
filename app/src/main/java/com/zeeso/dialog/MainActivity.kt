package com.zeeso.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeeso.edittextdialog.EditTextDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EditTextDialog(0).show(supportFragmentManager, "tag")
    }
}