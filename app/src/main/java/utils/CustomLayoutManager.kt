package utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

class CustomLayoutManager(
    context: Context,
    private var isScrollEnabled: Boolean = true
) : LinearLayoutManager(context) {

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}