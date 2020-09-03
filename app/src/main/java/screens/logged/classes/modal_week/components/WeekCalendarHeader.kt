package screens.logged.classes.modal_week.components

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarBinding
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderBinding
import com.joao.simsschool.databinding.ModalWeekCalendarHeaderItemBinding

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

}