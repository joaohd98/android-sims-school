package screens.logged.classes.modal_week

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalWeekCalendarBinding

class WeekCalendarModal : BottomSheetDialogFragment() {
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

}