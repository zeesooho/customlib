package com.zeeso.edittextdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class EditTextDialog(
    private val sizeOfList: Int,
    private val defaultList: List<String> = listOf(),
    private val hintList: List<String> = listOf()
): DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}