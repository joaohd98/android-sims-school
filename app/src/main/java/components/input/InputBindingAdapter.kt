package components.input

import androidx.databinding.BindingAdapter

object InputBindingAdapter {
    @JvmStatic @BindingAdapter("inputModel")
    fun setViewModel(view: InputView, inputModel: InputModel) {
        view.binding.inputModel = inputModel
    }
}