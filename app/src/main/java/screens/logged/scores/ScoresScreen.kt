package screens.logged.scores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.screen_scores.view.*
import kotlinx.android.synthetic.main.view_scores_classes.*
import kotlinx.android.synthetic.main.view_scores_semesters.*
import repositories.RepositoryStatus
import screens.logged.home.HomeViewModel
import screens.logged.scores.components.ScoresClassesAdapter
import screens.logged.scores.components.ScoresSemestersAdapter
import utils.addSkeletonAllElementsInner
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
        viewModel.statusScore.observe(viewLifecycleOwner, {
            when(it) {
                RepositoryStatus.FAILED -> {

                }
                RepositoryStatus.LOADING -> {

                }

                RepositoryStatus.SUCCESS -> {

                }
            }
        })
    }

    private fun initSemesters() {
        val viewManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.HORIZONTAL, false
        )

        val viewAdapter = ScoresSemestersAdapter(8)

        view_scores_semesters_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter

            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
            itemDecorator.setDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.empty_divider_width
                )!!
            )

            addItemDecoration(itemDecorator)
        }


    }

    private fun initClasses() {
        val viewManager = LinearLayoutManager(requireContext())
        val viewAdapter = ScoresClassesAdapter(8)

        view_scores_classes_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            isNestedScrollingEnabled = false
        }
    }
}