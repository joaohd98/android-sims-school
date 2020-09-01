package utils

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["background_tint_from_id"])
fun backgroundTintFromID(view: View, value: Int?) {
    if(value == null) {
        return
    }

    view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, value))
}
