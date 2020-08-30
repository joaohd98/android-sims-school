package components.progress_bar_text

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsProgressBarTextBinding

class ProgressBarText: LinearLayout {
    lateinit var binding: ComponentsProgressBarTextBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.components_progress_bar_text, this, true)
        }
        else {
            binding = ComponentsProgressBarTextBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }
}