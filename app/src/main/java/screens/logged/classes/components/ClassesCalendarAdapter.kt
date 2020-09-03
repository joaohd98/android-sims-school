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
import kotlin.collections.ArrayList

class ClassesCalendarAdapter(
    calendar: ArrayList<CalendarResponse>,
    val fragmentManager: FragmentManager,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val TYPE_MONTH = 0
        const val TYPE_WEEK = 1
    }

    private class ViewModel(
        val typeView: Int,
        val month: String?,
        val response: CalendarWeekResponse?
    )

    private var viewsModel: ArrayList<ViewModel> = arrayListOf()
    var actualMonthIndex = 0

    init {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        calendar.forEachIndexed { index, month ->
            if(index == currentMonth) {
                actualMonthIndex = viewsModel.size
            }

            viewsModel.add(ViewModel(
                TYPE_MONTH,
                month.name,
                null
            ))

            month.weeks.forEach { week ->
                viewsModel.add(ViewModel(
                    TYPE_WEEK,
                    null,
                    week
                ))
            }
        }
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
            is ViewWeekHolder -> holder.bind(viewsModel[position].response!!)
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
        fun bind(weekResponse: CalendarWeekResponse) {
            binding.viewClassesCalendarWeekLinearLayout.forEachIndexed { index, view ->
                val calendarItem = view as ClassesCalendarItem
                val dayResponse = weekResponse.days.find { index == it.weekDay }

                if(dayResponse != null) {
                    calendarItem.binding.text = dayResponse.day.split("/")[0]
                    calendarItem.alpha = 1f

                    if(dayResponse.course == "") {
                        calendarItem.binding.hasBullet = false
                        calendarItem.binding.linearLayoutClick = null
                    }
                    else {
                        calendarItem.binding.hasBullet = true
                        calendarItem.binding.hasBulletFill = dayResponse.homework != "" || dayResponse.test != ""
                        calendarItem.binding.linearLayoutClick = object: OnClickDataBinding {
                            override fun onClick() {
                                WeekCalendarModal.invoke(fragmentManager, weekResponse, dayResponse)
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
    }



}