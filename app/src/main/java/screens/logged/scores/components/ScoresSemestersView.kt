package screens.logged.scores.components

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresSemestersBinding
import kotlinx.android.synthetic.main.view_scores_semesters.view.*

class ScoresSemestersView : ConstraintLayout {
    lateinit var binding: ViewScoresSemestersBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_scores_semesters, this, true)
        }
        else {
            binding = ViewScoresSemestersBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initRecycleView(context: Context) {
        val viewAdapter = ScoresSemestersAdapter()

        view_scores_semesters_recycler_view.apply {
            layoutManager = CustomLayoutManager(context)
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

    fun setSuccess(actualSemester: Int) {
        val recyclerView = view_scores_semesters_recycler_view
        val adapter = recyclerView.adapter as ScoresSemestersAdapter
        val layoutManager = recyclerView.layoutManager as CustomLayoutManager


        layoutManager.isAbleScroll = true
        adapter.setSuccess(actualSemester)
    }
}

private class CustomLayoutManager(context: Context): LinearLayoutManager(context, HORIZONTAL, false) {
    var isAbleScroll = false

    override fun canScrollHorizontally(): Boolean {
        return isAbleScroll
    }
}