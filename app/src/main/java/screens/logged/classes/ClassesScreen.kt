package screens.logged.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.screen_classes.*
import repositories.RepositoryStatus
import utils.FragmentFromTab
import utils.observeOnce

class ClassesScreen : FragmentFromTab() {
    override val viewModel: ClassesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_classes, container, false)
    }

    override fun initMethod() {
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

                    view_classes_calendar.initRecyclerView(
                        viewModel.calendarResponse.value!!.recyclerViews,
                        viewModel.calendarResponse.value!!.recyclerViewsIndex,
                        activity?.supportFragmentManager!!
                    )
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

    companion object {
        fun newInstance() = ClassesScreen()
    }
}