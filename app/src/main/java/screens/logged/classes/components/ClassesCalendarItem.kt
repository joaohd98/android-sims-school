package screens.logged.classes.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarBinding
import com.joao.simsschool.databinding.ViewClassesCalendarItemBinding
import com.joao.simsschool.databinding.ViewHomeAdsBinding
import kotlinx.android.synthetic.main.view_classes_calendar.view.*
import repositories.ads.AdsResponse
import repositories.calendar.CalendarResponse
import screens.logged.scores.components.ScoresClassesAdapter

class ClassesCalendarItem: LinearLayout {
    lateinit var binding: ViewClassesCalendarItemBinding

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
            LayoutInflater.from(context).inflate(R.layout.view_classes_calendar_item, this, true)
        } else {
            binding =
                ViewClassesCalendarItemBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    private fun init() {
        binding.viewClassesCalendarItemLinearLayout.apply {
            setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.animate().alpha(0.3f).setDuration(100).start()
                    }
                    MotionEvent.ACTION_UP -> {
                        v.animate().alpha(1f).setDuration(100).start()
                        performClick()
                    }
                    else -> v.alpha = 1f
                }

                true
            }
        }
    }
}