package com.zeeso.dialog

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeeso.edittextdialog.EditTextDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        EditTextDialog.builder()
            .setTitle("title")
            .setSize(3)
            .setHints(listOf("아이디", "비번", "이름"))
            .setOnOkClickListener(object : EditTextDialog.Companion.OnClickListener {
                override fun onClick(inputList: List<String>) {
                    inputList.forEach { 
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            .setOnCancelClickListener(object : EditTextDialog.Companion.OnClickListener {
                override fun onClick(inputList: List<String>) {
                
                }
            })
            .setWindowSize(0.5f)
            .create()
            .show(supportFragmentManager, "TAG")
    }
}