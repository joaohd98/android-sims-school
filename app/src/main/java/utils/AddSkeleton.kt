package utils

import android.R.attr
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.view_home_classes.view.*


fun ViewGroup.addSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        val layoutParams =  LinearLayout.LayoutParams(view.layoutParams)
        layoutParams.setMargins(view.marginLeft, view.marginTop, view.marginRight, view.marginBottom)
        layoutParams.gravity = (view.layoutParams as LinearLayout.LayoutParams).gravity

        val shimmer = ShimmerFrameLayout(context)

        shimmer.background = ContextCompat.getDrawable(context, R.drawable.skeleton)
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