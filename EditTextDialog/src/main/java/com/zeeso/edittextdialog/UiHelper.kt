package com.zeeso.edittextdialog

import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlin.math.roundToInt

internal object UiHelper {
    // 화면 크기 받아오기
    private fun getWindowSize(windowManager: WindowManager): Point {
        val point = Point()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics = windowManager.currentWindowMetrics
            point.run {
                x = metrics.bounds.width()
                y = metrics.bounds.height()
            }
        } else @Suppress("DEPRECATION") {
            val display = windowManager.defaultDisplay
            display.getSize(point)
        }
        return point
    }
    
    // 화면 크기 설정
    fun Window.setMetrics(windowManager: WindowManager, x: Float = 1f, y: Float = 1f) {
        if (x > 1f || y > 1f) throw java.lang.IllegalArgumentException("x and y must be unconditionally less than or equal to 1.")
        val rect = getWindowSize(windowManager)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(this.attributes)
        if (x != 1f)
            layoutParams.width = (rect.x*x).roundToInt()
        if (y != 1f)
            layoutParams.height = (rect.y*y).roundToInt()
        this.attributes = layoutParams
    }
    
    // 뷰 크기 설정
    fun View.setMetrics(windowManager: WindowManager, x: Float = 1f, y: Float = 1f) {
        if (x > 1f || y > 1f) throw java.lang.IllegalArgumentException("x and y must be unconditionally less than or equal to 1.")
        val rect = getWindowSize(windowManager)
        
        if (x != 1f)
            layoutParams.width = (rect.x*x).roundToInt()
        if (y != 1f)
            layoutParams.height = (rect.y*y).roundToInt()
    }
}