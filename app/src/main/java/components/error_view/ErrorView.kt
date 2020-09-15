package components.error_view

import android.content.Context
import android.content.res.ColorStateList
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ComponentsViewErrorBinding
import kotlinx.android.synthetic.main.components_view_error.view.*
import utils.OnClickDataBinding

class ErrorView: LinearLayout {
    lateinit var binding: ComponentsViewErrorBinding

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
            LayoutInflater.from(context).inflate(R.layout.components_view_error, this, true)
        }
        else {
            binding = ComponentsViewErrorBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.ErrorView, defStyle, 0
        )

        val hasNoBackground = typedArray.getBoolean(
            R.styleable.ErrorView_hasNoBorder, false
        )

        if(hasNoBackground) {
            view_error_linear_layout.background = null
        }

        view_error_message.text = typedArray.getString(
            R.styleable.ErrorView_message
        )

        val color = typedArray.getColor(
            R.styleable.ErrorView_messageColor, 0
        )

        if(color != 0) {
            view_error_image.setColorFilter(color)
            view_error_message.setTextColor(color)
            view_error_sub_message.setTextColor(color)
        }

        typedArray.recycle()
    }

    fun setTryAgain(onTryAgain: OnTryAgainClickDataBinding) {
        binding.tryAgain = object: OnClickDataBinding() {
            override fun onClick() {
                onTryAgain.showLoading()

                Handler().postDelayed({
                    onTryAgain.onClick()
                }, 1500)
            }
        }
    }
}
