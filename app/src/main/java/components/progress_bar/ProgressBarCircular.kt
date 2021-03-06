package components.progress_bar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsProgressBarCircularBinding
import kotlinx.android.synthetic.main.components_progress_bar_circular.view.*

class ProgressBarCircular: LinearLayout {
    lateinit var binding: ComponentsProgressBarCircularBinding

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
            LayoutInflater.from(context).inflate(R.layout.components_progress_bar_circular, this, true)
        }
        else {
            binding = ComponentsProgressBarCircularBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.ProgressBarCircular, defStyle, 0
        )

        components_progress_bar_circular_text.text = typedArray.getString(
            R.styleable.ProgressBarCircular_circularTopText
        )

        typedArray.recycle()
    }

    fun removeLoading() {
        binding.componentsProgressBarCircularShimmer.hideShimmer()
    }
}