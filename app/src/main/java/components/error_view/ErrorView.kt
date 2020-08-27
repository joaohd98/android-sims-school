package components.error_view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewErrorBinding
import com.joao.simsschool.databinding.ViewInputBinding
import kotlinx.android.synthetic.main.view_error.view.*

class ErrorView: LinearLayout {
    lateinit var binding: ViewErrorBinding

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
            LayoutInflater.from(context).inflate(R.layout.view_error, this, true)
        }
        else {
            binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.ErrorView, defStyle, 0
        )

        view_error_message.text = typedArray.getString(
            R.styleable.ErrorView_message
        )

        typedArray.recycle()
    }
}
