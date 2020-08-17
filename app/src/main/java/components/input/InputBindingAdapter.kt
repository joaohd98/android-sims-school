package components.input

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.joao.simsschool.R

object InputBindingAdapter {
    @JvmStatic @BindingAdapter("inputModel")
    fun setViewModel(view: InputView, inputModel: InputModel) {
        view.binding.inputModel = inputModel

        val edit = view.findViewById<EditText>(R.id.view_input)
        edit.addTextChangedListener(inputModel.getChangeTextWatcher())
        edit.onFocusChangeListener = inputModel.getOnFocusChangeListener()
    }
}