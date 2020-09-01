package components.progress_bar

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter


@BindingAdapter(
    value = [
        "progress_bar_horizontal_course_value",
        "progress_bar_horizontal_course_colors"
    ], requireAll = true
)
fun progressBarHorizontalValue(view: ProgressBarHorizontal, value: Int?, colors: Pair<Int, Int>?) {
    if(value == null || colors == null) {
        return
    }

    view.binding.value = value.toString()
    view.binding.componentsProgressBarHorizontalBar.apply {
        progressBackgroundTintList = ContextCompat.getColorStateList(view.context, colors.first)
    }
    view.binding.progressTint = ContextCompat.getColor(view.context, colors.second)

    val regex = Regex("[^A-Za-z0-9 ]")
    val progressValue = regex.replace(value.toString(), "").toDouble()

    when {
        progressValue <= 10 -> {
            view.binding.progressValue = (progressValue / 10 * 100).toInt()
        }
        progressValue <= 100 -> {
            view.binding.progressValue = progressValue.toInt()
        }
    }
}

