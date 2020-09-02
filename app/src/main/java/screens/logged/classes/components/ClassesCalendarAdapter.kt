package screens.logged.classes.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarMonthBinding
import com.joao.simsschool.databinding.ViewClassesCalendarWeekBinding

class ClassesCalendarAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_MONTH = 1
        const val TYPE_WEEK = 2
    }

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

            return ViewWeekHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ViewWeekHolder -> holder.bind(position)
            is ViewMontHolder -> holder.bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 1) {
            TYPE_MONTH
        } else {
            TYPE_WEEK
        }
    }

    override fun getItemCount() = 20

    class ViewWeekHolder(
        private val binding: ViewClassesCalendarWeekBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
        }
    }

    class ViewMontHolder(
        private val binding: ViewClassesCalendarMonthBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
        }
    }

}