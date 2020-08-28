package components.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsViewInputBinding

class InputView: LinearLayout {
    lateinit var binding: ComponentsViewInputBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.components_view_input, this, true)
        }
        else {
            binding = ComponentsViewInputBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }
}
