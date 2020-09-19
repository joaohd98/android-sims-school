package screens.logged.classes.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.forEachIndexed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarMonthBinding
import com.joao.simsschool.databinding.ViewClassesCalendarWeekBinding
import repositories.calendar.CalendarResponse
import repositories.calendar.CalendarWeekResponse
import screens.logged.classes.modal_week.WeekCalendarModal
import utils.OnClickDataBinding
import java.util.*

class ClassesCalendarAdapter(
    private val viewsModel: ArrayList<CalendarResponse.RecyclerViewModel>,
    private val fragmentManager: FragmentManager,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MONTH = 0
        const val TYPE_WEEK = 1
    }

    override fun getItemCount() = viewsModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_MONTH) {
            val binding =  DataBindingUtil
                .inflate<ViewClassesCalendarMonthBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.view_classes_calendar_month,
                    parent,
                    false
                )

            return ViewMontHolder(binding)
        }

        else {
            val binding =  DataBindingUtil
                .inflate<ViewClassesCalendarWeekBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.view_classes_calendar_week,
                    parent,
                    false
                )

            return ViewWeekHolder(binding, fragmentManager)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewMontHolder -> holder.bind(viewsModel[position].month!!)
            is ViewWeekHolder -> holder.bind(viewsModel, viewsModel[position].response!!, position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewsModel[position].typeView == TYPE_MONTH) {
            TYPE_MONTH
        } else {
            TYPE_WEEK
        }
    }

    class ViewMontHolder(
        private val binding: ViewClassesCalendarMonthBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(month: String) {
            binding.month = month
            binding.year = Calendar.getInstance().get(Calendar.YEAR).toString()

            binding.executePendingBindings()
        }
    }

    class ViewWeekHolder(
        private val binding: ViewClassesCalendarWeekBinding,
        private val fragmentManager: FragmentManager
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            viewsModel: ArrayList<CalendarResponse.RecyclerViewModel>,
            weekResponse: CalendarWeekResponse,
            position: Int
        ) {
            binding.viewClassesCalendarWeekLinearLayout.forEachIndexed { index, view ->
                val calendarItem = view as ClassesCalendarItem
                val dayResponse = weekResponse.days.find { index == it.weekDay }

                if(dayResponse != null) {
                    calendarItem.binding.text = dayResponse.getDayFormatted()
                    calendarItem.alpha = 1f

                    if(dayResponse.course == "") {
                        calendarItem.binding.hasBullet = false
                        calendarItem.binding.linearLayoutClick = null
                    }
                    else {
                        calendarItem.binding.hasBullet = true
                        calendarItem.binding.hasBulletFill = dayResponse.homework != "" || dayResponse.test != ""
                        calendarItem.binding.linearLayoutClick = object: OnClickDataBinding() {
                            override fun onClick() {
                                WeekCalendarModal.invoke(
                                    fragmentManager,
                                    getCompleteWeekResponse(viewsModel, weekResponse, position),
                                    dayResponse
                                )
                            }
                        }
                    }
                }
                else {
                    calendarItem.alpha = 0f
                    calendarItem.binding.hasBullet = false
                    calendarItem.binding.hasBulletFill = false
                    calendarItem.binding.linearLayoutClick = null
                }
            }

            binding.executePendingBindings()
        }

        fun getCompleteWeekResponse(
            viewsModel: ArrayList<CalendarResponse.RecyclerViewModel>,
            weekResponse: CalendarWeekResponse,
            position: Int)
        : CalendarWeekResponse {
            val size = viewsModel.size - 1
            var isLastOrFirst = false

            val replacerWeekResponse: CalendarWeekResponse? = when {
                position <= 1 -> {
                    isLastOrFirst = true
                    viewsModel[size].response
                }
                position >= size -> {
                    isLastOrFirst = true
                    viewsModel[1].response
                }
                viewsModel[position - 1].month != null -> {
                    viewsModel[position - 2].response
                }
                viewsModel[position + 1].month != null -> {
                    viewsModel[position + 2].response
                }
                else -> {
                    null
                }
            }

            return if(replacerWeekResponse != null && !isLastOrFirst) {
                replacerWeekResponse.days.forEach { replacer ->
                    val actual = weekResponse.days.find { it.weekDay == replacer.weekDay }

                    if(actual == null) {
                        weekResponse.days.add(replacer)
                    }
                }

                weekResponse
            } else {
                weekResponse
            }
        }
    }
}