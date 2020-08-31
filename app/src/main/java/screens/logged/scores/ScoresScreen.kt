package screens.logged.scores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import kotlinx.android.synthetic.main.screen_scores.view.*
import kotlinx.android.synthetic.main.view_scores_classes.*
import kotlinx.android.synthetic.main.view_scores_semesters.*
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
        val viewManager = LinearLayoutManager(requireContext())
        val viewAdapter = ScoresClassesAdapter(8)
        var checkScrollingUp = true

        view_scores_classes_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter

            isNestedScrollingEnabled = false
//            addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    val view = view_scores_semesters_recycler_view
//                    val height = view.layoutManager?.height!!
//
//
//                    Log.d("aaa", dy.toString())
//                    Log.d("aaa", height.toString())
//
//                    if (dy > height) {
//                        view.visibility = View.GONE
//                    }
//                    else {
//                        view.visibility = View.VISIBLE
//                    }
//
//                }
//            })
        }
    }
}