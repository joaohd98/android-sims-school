package components.input

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.joao.simsschool.databinding.ViewInputBinding

class InputView : ConstraintLayout {
    private val binding: ViewInputBinding = ViewInputBinding.inflate(LayoutInflater.from(context), this, true)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    companion object {
        @JvmStatic @BindingAdapter("inputModel")
        fun setViewModel(view: InputView, inputModel: InputModel) {
            view.binding.inputModel = inputModel
        }
    }

}
