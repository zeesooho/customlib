package com.zeeso.edittextdialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.DialogFragment
import com.zeeso.edittextdialog.UiHelper.setMetrics

class EditTextDialog internal constructor(
    private val size: Int,
    private val onOkClickListener: OnClickListener,
    private val onCancelClickListener: OnClickListener,
    private val title: String? = null,
    private val subTitle: String? = null,
    private val hintList: List<String> = listOf(),
    private val x: Float = 1f,
    private val y: Float = 1f
) : DialogFragment() {
    override fun onResume() {
        dialog?.window?.setMetrics(context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager, x, y)
        super.onResume()
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.sampledialog, container, false)
        
        dialog?.run {
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        
        title?.let {
            view.findViewById<TextView>(R.id.title_text).run {
                visibility = View.VISIBLE
                text = it
            }
        }
    
        subTitle?.let {
            view.findViewById<TextView>(R.id.sub_title_text).run {
                visibility = View.VISIBLE
                text = it
            }
        }
    
        val containerLayout: LinearLayoutCompat = view.findViewById(R.id.edit_text_layout)
        val viewList: MutableList<EditText> = mutableListOf()
        for (i in 0 until this.size) {
            EditText(context).run {
                id = View.generateViewId()
                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                hint = if (hintList.size >= this@EditTextDialog.size) hintList[i] else ""
                containerLayout.addView(this)
                viewList.add(this)
            }
        }
        view.findViewById<Button>(R.id.button_ok).setOnClickListener {
            onOkClickListener.onClick(viewList.map { it.text.toString() })
            dismiss()
        }
        view.findViewById<Button>(R.id.button_cancel).setOnClickListener {
            onCancelClickListener.onClick(viewList.map { it.text.toString() })
            dismiss()
        }
    
        return view
    }
    
    companion object {
        class Builder {
            private var title: String? = null
            fun setTitle(message: String): Builder {
                this.title = message
                return this
            }
            
            private var subTitle: String? = null
            fun setSubTitle(message: String): Builder {
                this.subTitle = message
                return this
            }
            
            private var size: Int = 1
            fun setSize(size: Int): Builder {
                this.size = size
                return this
            }
            
            private var hintList: MutableList<String> = mutableListOf()
            fun setHints(hintList: List<String>): Builder {
                this.hintList = hintList.toMutableList()
                return this
            }
            
            private var onOkClickListener: OnClickListener = object : OnClickListener {
                override fun onClick(inputList: List<String>) {
                }
            }
            private var onCancelClickListener: OnClickListener = object : OnClickListener {
                override fun onClick(inputList: List<String>) {
                }
            }
            
            fun setOnOkClickListener(listener: OnClickListener): Builder {
                this.onOkClickListener = listener
                return this
            }
            
            fun setOnCancelClickListener(listener: OnClickListener): Builder {
                this.onCancelClickListener = listener
                return this
            }
            
            private var x: Float = 1f
            private var y: Float = 1f
    
            private fun setWindowSize(x: Float, y: Float): Builder {
                this.x = x
                this.y = y
                return this
            }
    
            fun setWindowSize(x: Float): Builder {
                return setWindowSize(x, 1f)
            }
            
            fun create(): EditTextDialog {
                return EditTextDialog(size, onOkClickListener, onCancelClickListener, title, subTitle, hintList, x, y)
            }
        }
        
        interface OnClickListener {
            fun onClick(inputList: List<String>)
        }
        
        fun builder(): Builder {
            return Builder()
        }
    }
}