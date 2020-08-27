package screens.logged.home.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeClassesContainerBinding
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.view_home_classes_container.view.*
import repositories.classes.ClassesResponse
import utils.getPixels


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
            LayoutInflater.from(context).inflate(R.layout.view_home_classes_container, this, true)
        }
        else {
            binding = ViewHomeClassesContainerBinding.inflate(
                LayoutInflater.from(context),
                this,
                true
            )


        }

    }

    fun setClasses(context: Context) {
        val viewPager = view_home_classes_container_view_pager

        viewPager.pageMargin = 100
        viewPager.adapter = HomeClassesViewAdapter(context)
    }

    fun setLoading() {
        val viewPager = view_home_classes_container_view_pager
        val adapter = viewPager.adapter as HomeClassesViewAdapter

        viewPager.setScrollingEnabled(false)
        adapter.showSkeleton()
    }

    fun setFailed() {
        view_home_classes_container_switcher.showNext()
    }

    fun setSuccess(classes: MutableList<ClassesResponse>, dayWeek: Int) {
        showDots(classes.size, dayWeek)

        val viewPager = view_home_classes_container_view_pager
        val pages = classes.map {
             HomeClassesView(context).apply { setClass(it) }
        }

        viewPager.adapter = HomeClassesViewAdapter(context, pages).apply {
            viewPager.setScrollingEnabled(true)
        }
        viewPager.setCurrentItem((classes.size * 100000) + dayWeek, false)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                changeSelectedDot(position % classes.size)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun showDots(size: Int, actualIndex: Int) {
        val dots = view_home_classes_container_dots

        for (i in 1..size) {
            val dot = ImageView(context);
            dot.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_action_bullet))

            val pixels = context.getPixels(15)
            val params = LinearLayout.LayoutParams(pixels, pixels)
            dots.addView(dot, params)
        }

        changeSelectedDot(actualIndex)
    }

    private fun changeSelectedDot(selectedIndex: Int) {
        val dots = view_home_classes_container_dots

        for (i in 0 until dots.size) {
            val drawableId  =
                if (i == selectedIndex)
                    R.drawable.ic_action_bullet_fill
                else
                    R.drawable.ic_action_bullet

            val drawable = ContextCompat.getDrawable(context, drawableId)!!

            (dots[i] as ImageView).setImageDrawable(drawable)
        }
    }

    fun setTryAgain(onTryAgain: () -> Unit) {
        binding.tryAgain = object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                view_home_classes_container_switcher.showNext()
            }
            override fun onClick() {
                onTryAgain()
            }
        }
    }
}