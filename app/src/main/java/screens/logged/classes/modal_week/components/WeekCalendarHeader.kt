package screens.logged.classes.modal_week.components

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarBinding
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderBinding
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderItemBinding
import repositories.calendar.CalendarDayResponse
import repositories.calendar.CalendarWeekResponse

class WeekCalendarHeader: ConstraintLayout {
    lateinit var binding: ModalWeekCalendarHeaderBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.modal_week_calendar_header, this, true)
        }
        else {
            binding = ModalWeekCalendarHeaderBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun setHeader(
        weekResponse: CalendarWeekResponse,
        dayResponse: CalendarDayResponse,
        onChangeDay: (day: CalendarDayResponse) -> Unit
    ) {
        binding.modalWeekCalendarHeaderLinearLayout.forEachIndexed { index, view ->
            (view as WeekCalendarHeaderItem).apply {
                val dayActual = weekResponse.days.find { index == it.weekDay }

                val onChangeHeader = fun() {
                    if(dayActual != null) {
                        setHeader(weekResponse, dayActual, onChangeDay)
                        onChangeDay(dayActual)
                    }
                }

                if(dayActual != null) {
                    initItem(index, dayActual.getDayFormatted(), dayActual.weekDay == dayResponse.weekDay, onChangeHeader)
                }
                else {
                    initItem(index, "", false, onChangeHeader)
                }
            }
        }
    }

}