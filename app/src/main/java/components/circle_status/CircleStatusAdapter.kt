package components.circle_status

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import components.progress_bar.ProgressBarCircular

@BindingAdapter(value = ["circle_status_quantity"])
fun circleStatusQuantity(view: CircleStatus, value: Int?) {
    if(value == null || value == 0) {
        return
    }

    view.drawnStatus(value)
}

