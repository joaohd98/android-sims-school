package components.circle_status

import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["circle_status_quantity"])
fun circleStatusQuantity(view: CircleStatus, value: Int?) {
    if(value == null || value == 0) {
        return
    }

    view.drawnStatus(value)
}

