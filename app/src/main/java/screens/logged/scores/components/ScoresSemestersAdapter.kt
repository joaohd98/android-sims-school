package screens.logged.scores.components

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresSemestersCircleBinding
import utils.getPixels

class ScoresSemestersAdapter(
    private var actualSemester: Int = MaxSemesters,
): RecyclerView.Adapter<ScoresSemestersAdapter.ViewHolder>() {

    lateinit var context: Context
    var isLoading = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val binding =  DataBindingUtil
            .inflate<ViewScoresSemestersCircleBinding>(
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

        holder.bind(position + 1, isLoading)
    }

    override fun getItemCount() = actualSemester

    class ViewHolder(
        private val binding: ViewScoresSemestersCircleBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(semester: Int, isLoading: Boolean) {
            binding.semester = semester.toString()

            if(isLoading) {
                setLoading()
            }

            else  {
                setSuccess()
            }
        }

        private fun setLoading() {
            binding.isLoading = true
            binding.executePendingBindings()
        }

        private fun setSuccess() {
            val shimmer = binding.viewScoresSemestersShimmer
            shimmer.hideShimmer()

            binding.isLoading = false
            binding.executePendingBindings()
        }
    }

    fun setLoading() {
        actualSemester = MaxSemesters
        isLoading = true

        notifyDataSetChanged()
    }


    fun setSuccess(value: Int) {
        actualSemester = value
        isLoading = false

        notifyDataSetChanged()
    }

    companion object {
        const val MaxSemesters = 20
    }
}