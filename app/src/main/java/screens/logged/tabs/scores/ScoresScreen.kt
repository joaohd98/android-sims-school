package screens.logged.tabs.scores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.joao.simsschool.R
import components.error_view.OnTryAgainClickDataBinding
import kotlinx.android.synthetic.main.screen_scores.*
import repositories.RepositoryStatus
import screens.logged.tabs.scores.components.ScoresClassesAdapter
import utils.observeOnce


class ScoresScreen : Fragment() {
    private val viewModel: ScoresViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        callRequests()
        initSemesters()
        initClasses()
        initErrorView()
        setObserves()
    }

    private fun callRequests() {
        viewModel.user.observeOnce(viewLifecycleOwner) {
            if (it != null) {
                viewModel.callScores()
            }
        }
    }

    private fun setObserves() {
        val scrollView = view_scores_scroll_view
        val viewSwitcher = view_scores_view_switcher
        val viewSemesters = view_scores_semesters
        val viewClasses = view_scores_classes

        val actualSemesterObserve = fun() {
            viewModel.actualSemester.observe(viewLifecycleOwner, {
                viewSemesters.binding.viewScoresSemestersRecyclerView.apply {
                    adapter!!.notifyDataSetChanged()
                }

                viewClasses.binding.viewScoresClassesRecyclerView.apply {
                    val adapter = adapter!! as ScoresClassesAdapter
                    adapter.setScores(viewModel.getActualScores())
                    scrollView.scrollTo(0, 0)
                }
            })
        }

        viewModel.statusScore.observe(viewLifecycleOwner, {
            when(it) {
                RepositoryStatus.FAILED -> {
                    viewSwitcher.showNext()
                }
                RepositoryStatus.LOADING -> {
                    scrollView.setScrollingEnabled(false)
                }
                RepositoryStatus.SUCCESS -> {
                    val scores = viewModel.scores

                    viewSemesters.setSuccess(scores.size)
                    viewClasses.setSuccess(viewModel.getActualScores())
                    actualSemesterObserve()
                    scrollView.setScrollingEnabled(true)
                }
                else -> {}
            }
        })
    }

    private fun initSemesters() {
        view_scores_semesters.initRecycleView(requireContext(), viewModel.actualSemester)
    }

    private fun initClasses() {
        view_scores_classes.initRecyclerView(requireContext())
    }

    private fun initErrorView() {
        view_scores_error_view.setTryAgain(object: OnTryAgainClickDataBinding {
            override fun showLoading() {
                view_scores_view_switcher.showNext()
            }

            override fun onClick() {
                viewModel.callScores()
            }
        })
    }
}