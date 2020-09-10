package utils

import android.view.View
import androidx.viewpager2.widget.ViewPager2

class CubeTransformer : ViewPager2.PageTransformer {
    override fun transformPage(view: View, position: Float) {
        val deltaY = 0.25F

        view.pivotX = if (position < 0F) view.width.toFloat() else 0F
        view.pivotY = view.height * deltaY
        view.rotationY = 25F * position
    }
}