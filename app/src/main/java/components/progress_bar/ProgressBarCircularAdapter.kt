package components.progress_bar

import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["progress_bar_circular_value"])
fun progressBarCircularValue(view: ProgressBarCircular, value: String?) {
    view.binding.componentsProgressBarCircularValue.text = value ?: ""
}