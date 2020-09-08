package screens.logged.tips.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarBinding
import com.joao.simsschool.databinding.ViewTipsListBinding
import repositories.calendar.CalendarResponse
import repositories.tips.TipsResponse
import screens.logged.classes.components.ClassesCalendarAdapter

class TipsListView: LinearLayout {
    lateinit var binding: ViewTipsListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_tips_list, this, true)
        }
        else {
            binding = ViewTipsListBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initRecyclerView(fragmentManager: FragmentManager) {
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = TipsListAdapter(fragmentManager)

        binding.viewTipsListRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}