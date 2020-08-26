package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeClassesBinding
import kotlinx.android.synthetic.main.view_home_classes.view.*
import utils.addSkeletonAllElementsInner
import utils.removeSkeletonAllElementsInner


class HomeClassesView : ConstraintLayout {
    lateinit var binding: ViewHomeClassesBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_home_classes, this, true)
        }
        else {
            binding = ViewHomeClassesBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun showSkeleton() {
        linear_layout.addSkeletonAllElementsInner()
    }

    fun hideSkeleton() {
        linear_layout.removeSkeletonAllElementsInner()
    }
}
