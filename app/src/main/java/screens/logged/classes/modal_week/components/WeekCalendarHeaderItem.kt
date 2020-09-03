package screens.logged.classes.modal_week.components

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarBinding
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderItemBinding
import com.joao.simsschool.databinding.ViewClassesCalendarBinding

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

}