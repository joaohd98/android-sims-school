package utils

import android.content.Context


fun Context.getPixels(dp: Int): Int {
    val scale = this.resources.displayMetrics.density

    return (dp * scale).toInt()
}