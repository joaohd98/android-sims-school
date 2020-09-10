package screens.logged.tabs.classes.modal_week.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderItemBinding
import utils.OnClickDataBinding

class WeekCalendarHeaderItem : LinearLayout {
    lateinit var binding: ModalWeekCalendarHeaderItemBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_week_calendar_header_item, this, true)
        }
        else {
            binding = ModalWeekCalendarHeaderItemBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initItem(dayWeek: Int, dayMonth: String?, isSelected: Boolean, onClick: () -> Unit) {
        val weekDays = resources.getStringArray(R.array.week)

        binding.dayWeek = weekDays[dayWeek]
        binding.dayMonth = dayMonth
        binding.isSelected = isSelected
        binding.changeHeader = object: OnClickDataBinding() {
            override fun onClick() {
                onClick()
            }
        }

        binding.modalWeekCalendarHeaderItemLinearLayout.apply {
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