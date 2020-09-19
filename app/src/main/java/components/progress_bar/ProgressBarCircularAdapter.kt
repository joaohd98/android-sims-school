package components.progress_bar

import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(value = [
    "progress_bar_circular_course_value",
    "progress_bar_circular_course_colors"
], requireAll = true)
fun progressBarCircularValue(view: ProgressBarCircular, value: String?, colors: Pair<Int, Int>?) {
    if(value == null || colors == null) {
        return
    }

    view.binding.value = value
    view.binding.backgroundTint = ContextCompat.getColor(view.context, colors.first)
    view.binding.progressTint = ContextCompat.getColor(view.context, colors.second)

    val regex = Regex("[^A-Za-z0-9 ]")
    val progressValue = regex.replace(value, "").toDouble()

    when {
        progressValue <= 10 -> {
            view.binding.progressValue = (progressValue / 10 * 100).toInt()
        }
        progressValue <= 100 -> {
            view.binding.progressValue = progressValue.toInt()
        }
    }
}

