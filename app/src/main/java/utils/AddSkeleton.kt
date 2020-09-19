package utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R


fun View.addSkeleton() {
    val shimmer = getShimmer(this, context)
    val parent = parent as ViewGroup

    parent.removeView(this)
    shimmer.addView(this)
    parent.addView(shimmer, 0)
}


fun View.removeSkeleton() {
//    val shimmer = getShimmer(this, context)
//    val parent = parent as ViewGroup
//
//    parent.removeView(this)
}

fun ViewGroup.addSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        if(view is ViewGroup) {
            return@forEachIndexed
        }

        val shimmer = getShimmer(view, context)

        removeView(view)
        shimmer.addView(view)
        addView(shimmer, index)
    }
}

fun ViewGroup.removeSkeletonAllElementsInner() {
    this.forEachIndexed { index, view ->
        val shimmer = view as ShimmerFrameLayout
        val subView = shimmer[0]

        shimmer.removeView(subView)
        removeView(shimmer)
        addView(subView, index)
    }
}



private fun getShimmer(view: View, context: Context): ShimmerFrameLayout {
    val layoutParams =  LinearLayout.LayoutParams(view.layoutParams)

    layoutParams.setMargins(view.marginLeft, view.marginTop, view.marginRight, view.marginBottom)
    layoutParams.gravity = (view.layoutParams as LinearLayout.LayoutParams).gravity

    val shimmer = ShimmerFrameLayout(context)

    shimmer.background = ContextCompat.getDrawable(context, R.drawable.skeleton)
    shimmer.layoutParams = layoutParams

    return shimmer
}