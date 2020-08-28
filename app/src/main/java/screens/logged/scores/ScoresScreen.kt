package screens.logged.scores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.view_scores_classes.*
import kotlinx.android.synthetic.main.view_scores_classes_card.*
import kotlinx.android.synthetic.main.view_scores_semesters.*
import kotlinx.android.synthetic.main.view_scores_semesters.view_scores_semesters_recycler_view
import screens.logged.scores.components.ScoresClassesAdapter
import screens.logged.scores.components.ScoresSemestersAdapter


class ScoresScreen : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.screen_scores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSemesters()
        initClasses()
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
        val viewManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        val viewAdapter = ScoresClassesAdapter(8)

        view_scores_classes_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}