package utils

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.core.view.get
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.view_home_classes.view.*

fun ViewGroup.addSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        val shimmer = ShimmerFrameLayout(context)

        shimmer.background = ContextCompat.getDrawable(context, R.drawable.skeleton)
        val layoutParams = ViewGroup.LayoutParams(view.layoutParams)
        shimmer.layoutParams = layoutParams

        linear_layout.removeView(view)
        shimmer.addView(view)
        linear_layout.addView(shimmer, index)
    }
}

fun ViewGroup.removeSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        val shimmer = view as ViewGroup
        val subView = shimmer[0]

        shimmer.removeView(subView)
        this.removeView(shimmer)
        linear_layout.addView(subView, index)
    }
}