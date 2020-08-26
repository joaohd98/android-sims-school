package utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


class CustomViewPager: ViewPager {
    private var isScrollingEnabled = true

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return isScrollingEnabled && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        return isScrollingEnabled && super.onInterceptTouchEvent(event)
    }

    fun setScrollingEnabled(b: Boolean) {
        isScrollingEnabled = b
    }

}