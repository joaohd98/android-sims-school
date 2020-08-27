package components.error_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewErrorBinding
import com.joao.simsschool.databinding.ViewInputBinding

class ErrorView: LinearLayout {
    lateinit var binding: ViewErrorBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_error, this, true)
        }
        else {
            binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }
}
