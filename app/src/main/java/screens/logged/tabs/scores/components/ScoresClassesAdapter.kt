package screens.logged.tabs.scores.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresClassesCardBinding
import components.progress_bar.ProgressBarCircular
import components.progress_bar.ProgressBarHorizontal
import repositories.scores.ScoresCourseResponse
import utils.getPixels


class ScoresClassesAdapter(
    private var scores: ArrayList<ScoresCourseResponse> = arrayListOf(
        ScoresCourseResponse(),
        ScoresCourseResponse(),
        ScoresCourseResponse(),
    )
) : RecyclerView.Adapter<ScoresClassesAdapter.ViewHolder>() {
    var isLoading = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =  DataBindingUtil.inflate<ViewScoresClassesCardBinding>(
            LayoutInflater.from(parent.context),
            R.layout.view_scores_classes_card,
            parent,
            false
        )

        return ViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!isLoading) {
            holder.bind(scores[position], isLoading)
        }
    }

    override fun getItemCount() = scores.size

    class ViewHolder(
        val context: Context,
        val binding: ViewScoresClassesCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(score: ScoresCourseResponse?, isLoading: Boolean) {
            binding.score = score

            setBackgroundColor(score)

            if(!isLoading) {
                hideLoadingScreen()
            }
        }

        private fun setBackgroundColor(score: ScoresCourseResponse?) {
            if(score == null)
                return

            val color = ContextCompat.getColor(context, score.getTextSituationColor())
            val background = binding.viewScoresClassesCardsSituation.background as GradientDrawable
            background.setStroke(context.getPixels(3), color)
        }

        private fun hideLoadingScreen() {
            val components = arrayOf(
                binding.viewScoresClassesCardsShimmerName,
                binding.viewScoresClassesCardsAverage,
                binding.viewScoresClassesCardsSkips,
                binding.viewScoresClassesCardsShimmerSituation,
                binding.viewScoresClassesCardsAv1,
                binding.viewScoresClassesCardsAv2,
            )

            components.forEach {
                when(it) {
                    is ShimmerFrameLayout -> it.apply {
                        hideShimmer()
                        background = null
                    }
                    is ProgressBarCircular -> it.removeLoading()
                    is ProgressBarHorizontal -> it.removeLoading()
                }
            }
        }
    }

    fun setScores(scores: ArrayList<ScoresCourseResponse>) {
        this.scores = scores
        notifyDataSetChanged()

    }

    fun setSuccess(scores: ArrayList<ScoresCourseResponse>) {
        this.scores = scores
        isLoading = false
        notifyDataSetChanged()
    }
}
