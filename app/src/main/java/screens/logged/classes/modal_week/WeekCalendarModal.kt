package screens.logged.classes.modal_week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarBinding
import repositories.calendar.CalendarDayResponse
import repositories.calendar.CalendarWeekResponse
import utils.CustomRoundBottomSheet

class WeekCalendarModal(
    private val weekResponse: CalendarWeekResponse,
    private var dayResponse: CalendarDayResponse
) : CustomRoundBottomSheet() {
    lateinit var binding: ModalWeekCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_week_calendar, container, false
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeader()
        initTasks()
    }

    private fun initHeader() {
        binding.modalWeekCalendarHeader.setHeader(weekResponse, dayResponse) {
            initTasks(it)
        }
    }

    private fun initTasks(day: CalendarDayResponse = dayResponse) {
        binding.modalWeekCalendarTasks.setTask(day)
    }

    companion object {
        fun invoke(
            fragmentManager: FragmentManager,
            weekResponse: CalendarWeekResponse,
            dayResponse: CalendarDayResponse
        ) {
            val bottomSheetFragment = WeekCalendarModal(weekResponse, dayResponse)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }
}