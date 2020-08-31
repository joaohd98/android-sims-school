package screens.logged.scores.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresSemestersCircleBinding
import utils.getPixels

class ScoresSemestersAdapter(private val actualSemester: Int) :
    RecyclerView.Adapter<ScoresSemestersAdapter.ViewHolder>() {

    lateinit var context: Context
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        context = parent.context

        val binding =  DataBindingUtil.inflate<ViewScoresSemestersCircleBinding>(
            LayoutInflater.from(context),
            R.layout.view_scores_semesters_circle,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(position == 0 || position == actualSemester - 1) {
            val view = holder.itemView
            val layoutParams = LinearLayout.LayoutParams(view.layoutParams)

            val value = context.getPixels(20)
            val marginLeft = if(position == 0) value else view.marginRight
            val marginRight  = if(position == actualSemester - 1) value else view.marginLeft

            layoutParams.setMargins(marginLeft, view.marginTop, marginRight, view.marginBottom)

            holder.itemView.layoutParams = layoutParams
        }

        holder.bind(position + 1)

    }
    override fun getItemCount() = actualSemester

    class ViewHolder(val binding: ViewScoresSemestersCircleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(semester: Number) {
            binding.semester = semester.toString()
            binding.executePendingBindings()
        }
    }

}