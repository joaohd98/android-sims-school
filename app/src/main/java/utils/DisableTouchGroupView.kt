package utils

import android.view.View
import android.view.ViewGroup

fun View.setLoading(enabled: Boolean, firstTry: Boolean = true) {
    isEnabled = enabled

    if (firstTry)
        alpha = if(enabled) 1f else 0.5f

    if (this is ViewGroup)
        (0 until childCount).map(::getChildAt).forEach { it.setLoading(enabled, false) }
}