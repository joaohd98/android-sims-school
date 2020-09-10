package utils

import android.view.MotionEvent
import android.view.View

abstract class OnClickDataBinding {
   abstract fun onClick()

   fun onClickAnimated(layout: View) {
      layout.apply {
         setOnTouchListener { v, event ->
            when (event.action) {
               MotionEvent.ACTION_DOWN -> {
                  v.animate().alpha(0.3f).setDuration(100).start()
               }
               MotionEvent.ACTION_UP -> {
                  v.animate().alpha(1f).setDuration(100).start()
                  performClick()
                  onClick()
               }
               else -> v.alpha = 1f
            }
            true
         }
      }
   }
}