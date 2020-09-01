package screens.logged.scores.components

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresSemestersCircleBinding
import utils.getPixels

class ScoresSemestersAdapter(
    private var actualSemester: MutableLiveData<Int>,
    private var size: Int = MaxSemesters,
): RecyclerView.Adapter<ScoresSemestersAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var isLoading = true

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(position, isLoading, size, actualSemester)

    override fun getItemCount() = size

    class ViewHolder(
        private val binding: ViewScoresSemestersCircleBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, isLoading: Boolean, size: Int, actualSemester: MutableLiveData<Int>) {
            val isSelected =  actualSemester.value!! == position

            binding.semester = (position + 1).toString()
            binding.isSelected = isSelected
            binding.hasMarginLeft = position == 0
            binding.hasMarginRight = position == size - 1

            showScreen(isLoading)

            if(!isLoading && !isSelected) {
                setClickable(position, actualSemester)
            }
        }

        private fun setClickable(position: Int, actualSemester: MutableLiveData<Int>) {
            binding.viewScoresSemestersCircle.setOnClickListener {
                actualSemester.value = position
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


    fun setSuccess(size: Int, recyclerView: RecyclerView) {
        this.size = size

        isLoading = false
        recyclerView.smoothScrollToPosition(actualSemester.value!!)

        notifyDataSetChanged()
    }

    companion object {
        const val MaxSemesters = 20
    }
}