package screens.logged.tabs.scores.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresClassesBinding
import kotlinx.android.synthetic.main.view_scores_classes.view.*
import repositories.scores.ScoresCourseResponse

class ScoresClassesView: ConstraintLayout {
    lateinit var binding: ViewScoresClassesBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_scores_classes, this, true)
        }
        else {
            binding = ViewScoresClassesBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initRecyclerView(context: Context) {
        val viewManager = LinearLayoutManager(context)
        val viewAdapter = ScoresClassesAdapter()

        view_scores_classes_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            isNestedScrollingEnabled = false
        }
    }

    fun setSuccess(scores: ArrayList<ScoresCourseResponse>) {
        val recyclerView = view_scores_classes_recycler_view
        val adapter = recyclerView.adapter as ScoresClassesAdapter

        adapter.setSuccess(scores)
    }

}