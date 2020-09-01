package screens.logged.scores.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresSemestersCircleBinding
import utils.getPixels

class ScoresSemestersAdapter(
    private var size: Int = MaxSemesters,
): RecyclerView.Adapter<ScoresSemestersAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var isLoading = true
    private var actualSemester = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val binding =  DataBindingUtil
            .inflate<ViewScoresSemestersCircleBinding>(
                LayoutInflater.from(context),
                R.layout.view_scores_semesters_circle,
                parent,
                false
            )

        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(position, isLoading, size, actualSemester)

    override fun getItemCount() = size

    class ViewHolder(
        private val binding: ViewScoresSemestersCircleBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, isLoading: Boolean, size: Int, actualSemester: Int) {
            val isSelected =  actualSemester == position

            binding.semester = (position + 1).toString()
            binding.isSelected = isSelected

            showScreen(isLoading)
            setClickable(isSelected)
            setMargin(position, size)
        }

        private fun setClickable(isSelected: Boolean) {
            if(isSelected)
                return

            binding.viewScoresSemestersCircle.setOnClickListener {

            }

        }

        private fun setMargin(position: Int, size: Int) {
            if(position == 0 || position == size - 1) {
                val view = itemView
                val layoutParams = LinearLayout.LayoutParams(view.layoutParams)

                val value = context.getPixels(20)
                val marginLeft = if(position == 0) value else view.marginRight
                val marginRight  = if(position == size - 1) value else view.marginLeft

                layoutParams.setMargins(marginLeft, view.marginTop, marginRight, view.marginBottom)

                itemView.layoutParams = layoutParams
            }
        }

        private fun showScreen(isLoading: Boolean) {
            if(isLoading) {
                binding.isLoading = true
            }

            else {
                val shimmer = binding.viewScoresSemestersShimmer
                shimmer.hideShimmer()

                binding.isLoading = false
            }

            binding.executePendingBindings()
        }
    }

    fun setLoading() {
        size = MaxSemesters
        isLoading = true

        notifyDataSetChanged()
    }


    fun setSuccess(size: Int, actualSemester: Int, recyclerView: RecyclerView) {
        this.size = size
        this.actualSemester = actualSemester

        isLoading = false

        recyclerView.smoothScrollToPosition(actualSemester)

        notifyDataSetChanged()
    }

    companion object {
        const val MaxSemesters = 20
    }
}