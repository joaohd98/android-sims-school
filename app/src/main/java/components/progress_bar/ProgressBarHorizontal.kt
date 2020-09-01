package components.progress_bar

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsProgressBarCircularBinding
import com.joao.simsschool.databinding.ComponentsProgressBarHorizontalBinding
import kotlinx.android.synthetic.main.components_progress_bar_circular.view.*
import kotlinx.android.synthetic.main.components_progress_bar_horizontal.view.*

class ProgressBarHorizontal: LinearLayout {
    lateinit var binding: ComponentsProgressBarHorizontalBinding

    constructor(context: Context) : super(context) {
        init(null, 0)
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.components_progress_bar_horizontal, this, true)
        }
        else {
            binding = ComponentsProgressBarHorizontalBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.ProgressBarHorizontal, defStyle, 0
        )

        binding.componentsProgressBarHorizontalTextLeft.text = typedArray.getString(
            R.styleable.ProgressBarHorizontal_horizontalLeftText
        )

        typedArray.recycle()
    }
}