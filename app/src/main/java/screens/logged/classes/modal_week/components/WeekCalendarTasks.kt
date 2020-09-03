package screens.logged.classes.modal_week.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarTasksBinding
import repositories.calendar.CalendarDayResponse

class WeekCalendarTasks: ConstraintLayout {
    lateinit var binding: ModalWeekCalendarTasksBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_week_calendar_tasks, this, true)
        }
        else {
            binding = ModalWeekCalendarTasksBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun setTask(dayResponse: CalendarDayResponse) {
        binding.dayResponse = dayResponse

        if(dayResponse.course == "") {
            binding.modalWeekCalendarTasksViewSwitcher.displayedChild = 1
        }
        else {
            binding.modalWeekCalendarTasksViewSwitcher.displayedChild = 0
        }
    }
}