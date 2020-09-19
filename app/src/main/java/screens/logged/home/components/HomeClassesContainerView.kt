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
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewHomeClassesContainerBinding
import components.error_view.OnTryAgainClickDataBinding
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

    fun setClasses(fragmentActivity: FragmentActivity, onError: () -> Unit) {
        val viewPager = binding.viewHomeClassesContainerViewPager
        viewPager.adapter = HomeClassesViewAdapter(fragmentActivity)

        setTryAgain(onError)
    }

    fun setLoading() {
        val viewPager = binding.viewHomeClassesContainerViewPager
        val adapter = viewPager.adapter as HomeClassesViewAdapter

        viewPager.isUserInputEnabled = false
        adapter.setLoading()
    }

    fun setFailed() {
        binding.viewHomeClassesContainerSwitcher.showNext()
    }

    fun setSuccess(classes: ArrayList<ClassesResponse>, dayWeek: Int) {
        showDots(classes.size, dayWeek)

        val viewPager = binding.viewHomeClassesContainerViewPager
        val adapter = viewPager.adapter as HomeClassesViewAdapter

        adapter.setClasses(classes)

        viewPager.apply {
            setCurrentItem((classes.size * 100000) + dayWeek, false)
            isUserInputEnabled = true

            setPageTransformer(MarginPageTransformer(62))


            registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    changeSelectedDot(position % classes.size)
                }
            })
        }
    }

    private fun showDots(size: Int, actualIndex: Int) {
        val dots = binding.viewHomeClassesContainerDots

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
        val dots = binding.viewHomeClassesContainerDots

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
                binding.viewHomeClassesContainerSwitcher.showNext()
            }
            override fun onClick() {
                onTryAgain()
            }
        }
    }
}