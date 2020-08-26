package utils

import android.view.ViewGroup
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

        view_home_classes_linear_layout_text.removeView(view)
        shimmer.addView(view)
        view_home_classes_linear_layout_text.addView(shimmer, index)
    }
}

fun ViewGroup.removeSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        val shimmer = view as ShimmerFrameLayout
        val subView = shimmer[0]


        shimmer.removeView(subView)
        removeView(shimmer)
        view_home_classes_linear_layout_text.addView(subView, index)
    }
}