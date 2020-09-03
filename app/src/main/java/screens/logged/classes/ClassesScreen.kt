package screens.logged.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import components.camera.CameraFragment
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.fragment_classes.*
import kotlinx.android.synthetic.main.screen_scores.*
import repositories.RepositoryStatus
import screens.logged.classes.modal_week.WeekCalendarModal
import screens.logged.home.HomeViewModel
import utils.observeOnce

class ClassesScreen : Fragment() {
    private val viewModel: ClassesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classes, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomSheetFragment = WeekCalendarModal()
        bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag)

        callRequests()
        initErrorView()
        setObserves()
    }

    private fun callRequests() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callCalendar()
            }
        }
    }

    private fun setObserves() {
        viewModel.calendarStatus.observe(viewLifecycleOwner) {
            when(it) {
                RepositoryStatus.FAILED -> {
                    view_classes_view_switcher_error_loading.showNext()
                }
                RepositoryStatus.SUCCESS -> {
                    view_classes_view_switcher.showNext()
                    view_classes_calendar.initRecyclerView(ArrayList(viewModel.calendarMonths))
                }
                else -> {}
            }
        }
    }

    private fun initErrorView() {
        view_classes_error_view.setTryAgain(object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                view_classes_view_switcher_error_loading.showNext()
            }

            override fun onClick() {
                viewModel.callCalendar(true )
            }
        })
    }

}