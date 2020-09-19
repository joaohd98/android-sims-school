package utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.widget.NestedScrollView

class CustomNestedScrollView: NestedScrollView {
    private var hasScroll = true;

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
    
    fun setScrollingEnabled(enabled: Boolean) {
        hasScroll = enabled
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when(ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                performClick()
                hasScroll and super.onTouchEvent(ev)
            }
            MotionEvent.ACTION_UP -> {
                performClick()
                hasScroll and super.onTouchEvent(ev)
            }
            else -> super.onTouchEvent(ev)
        }
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return hasScroll && super.onInterceptTouchEvent(ev)
    }
}