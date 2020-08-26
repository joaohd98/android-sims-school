package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeClassesContainerBinding
import kotlinx.android.synthetic.main.view_home_classes_container.view.*
import repositories.classes.ClassesResponse
import java.util.*

class HomeClassesContainerView: ConstraintLayout {
    lateinit var binding: ViewHomeClassesContainerBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_home_ads, this, true)
        }
        else {
            binding = ViewHomeClassesContainerBinding.inflate(LayoutInflater.from(context), this, true)
        }

    }

    fun setClasses(context: Context) {
        val viewPager = view_home_classes_container_view_pager

        viewPager.pageMargin = 100
        viewPager.adapter = HomeClassesViewAdapter(viewPager, context)
    }

    fun setLoading() {
        val viewPager = view_home_classes_container_view_pager
        val adapter = viewPager.adapter as HomeClassesViewAdapter

        viewPager.setScrollingEnabled(false)
        adapter.showSkeleton()
    }

    fun setFailed() {
    }

    fun setSuccess(classes: MutableList<ClassesResponse>, dayWeek: Int) {
        val viewPager = view_home_classes_container_view_pager

        val pages = classes.map {
             HomeClassesView(context).apply {
                 setClass(it)
            }
        }

        viewPager.adapter = HomeClassesViewAdapter(viewPager, context, pages).apply {
            viewPager.setScrollingEnabled(true)
        }

        viewPager.setCurrentItem(dayWeek, false)

    }

}