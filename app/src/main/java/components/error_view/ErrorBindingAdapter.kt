package components.error_view

import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["errorViewOnTryAgain"])
fun setTryAgain(view: ErrorView, onTryAgain: OnTryAgainClickDataBinding) {
    view.setTryAgain(onTryAgain)
}
