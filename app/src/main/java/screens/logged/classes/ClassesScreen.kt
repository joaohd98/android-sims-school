package screens.logged.classes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import repositories.RepositoryStatus
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

        callRequests()
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

                }
                RepositoryStatus.LOADING -> {

                }
                RepositoryStatus.SUCCESS -> {

                }
                else -> {}
            }
        }
    }

}