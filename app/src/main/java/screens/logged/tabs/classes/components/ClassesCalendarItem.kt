package screens.logged.tabs.classes.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewClassesCalendarItemBinding

class ClassesCalendarItem: LinearLayout {
    lateinit var binding: ViewClassesCalendarItemBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_classes_calendar_item, this, true)
        } else {
            binding =
                ViewClassesCalendarItemBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }


}