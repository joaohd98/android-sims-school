package screens.logged.classes.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarBinding
import com.joao.simsschool.databinding.ViewHomeAdsBinding
import kotlinx.android.synthetic.main.view_classes_calendar.view.*
import repositories.ads.AdsResponse
import repositories.calendar.CalendarResponse
import screens.logged.scores.components.ScoresClassesAdapter

class ClassesCalendar: ConstraintLayout {
    lateinit var binding: ViewClassesCalendarBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_classes_calendar, this, true)
        }
        else {
            binding = ViewClassesCalendarBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initRecyclerView(calendar: ArrayList<CalendarResponse>, fragmentManager: FragmentManager) {
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = ClassesCalendarAdapter(calendar, fragmentManager)

        binding.viewClassesCalendarRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            hasFixedSize()

            scrollToPosition(viewAdapter.actualMonthIndex)
        }
    }
}