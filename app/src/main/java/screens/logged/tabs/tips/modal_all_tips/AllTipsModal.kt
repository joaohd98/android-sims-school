package screens.logged.tabs.tips.modal_all_tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ModalAllTipsBinding
import com.joao.simsschool.databinding.ModalWeekCalendarBinding
import repositories.calendar.CalendarDayResponse
import repositories.calendar.CalendarWeekResponse
import repositories.tips.TipsResponse
import screens.logged.tabs.TabsPageAdapter
import screens.logged.tabs.classes.modal_week.WeekCalendarModal
import utils.CustomRoundBottomSheet

class AllTipsModal(
    private val tips: ArrayList<TipsResponse>,
    private val index: Int
): CustomRoundBottomSheet() {
    lateinit var binding: ModalAllTipsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.modal_all_tips, container, false
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setFullScreen()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = AllTipsAdapter(tips, context as FragmentActivity)

        binding.modalAllTipsViewPager.apply {
            this.adapter = adapter
            setCurrentItem(index, false)
        }
    }

    companion object {
        fun invoke(
            fragmentManager: FragmentManager,
            tips: ArrayList<TipsResponse>,
            index: Int
        ) {
            val bottomSheetFragment = AllTipsModal(tips, index)
            bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
        }
    }
}