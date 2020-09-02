package components.error_view

import android.os.Handler
import androidx.databinding.BindingAdapter
import utils.OnClickDataBinding

@BindingAdapter(value = ["errorViewOnTryAgain"])
fun setTryAgain(view: ErrorView, onTryAgain: OnTryAgainClickDataBinding) {
    view.setTryAgain(onTryAgain)
}
