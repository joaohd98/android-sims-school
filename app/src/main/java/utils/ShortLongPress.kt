package utils

import android.content.res.Resources
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup

fun View.shortLongPress(
    onStartPress: () -> Unit, onFinish: () -> Unit, onLongPress: () -> Unit, onShortPress: (MotionEvent) -> Unit) {

    var isLongPress = false
    val handler = Handler()

    val longPressed = Runnable {
        isLongPress = true
        onLongPress()
    }

    setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                onStartPress()
                isLongPress = false
                handler.postDelayed(longPressed, ViewConfiguration.getLongPressTimeout().toLong())
                v.performClick()
            }
            MotionEvent.ACTION_UP -> {
                handler.removeCallbacks(longPressed)
                onFinish()
                v.performClick()

                if(!isLongPress) {
                    onShortPress(event)
                }
            }
            else -> {}
        }
        false
    }
}