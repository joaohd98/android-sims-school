package screens.logged.tabs.classes.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEachIndexed
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesDaysWeekBinding


class ClassesDaysWeek: ConstraintLayout {
    lateinit var binding: ViewClassesDaysWeekBinding

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_classes_days_week, this, true)
        }
        else {
            binding = ViewClassesDaysWeekBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init() {
        val weekDays = resources.getStringArray(R.array.week)

        binding.viewClassesDaysWeekLinearLayout.forEachIndexed { index, view ->
            if(view is TextView) {
                view.text = weekDays[index]
            }
        }
    }
}