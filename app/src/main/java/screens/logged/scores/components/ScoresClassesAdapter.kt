package screens.logged.scores.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewScoresClassesCardBinding

class ScoresClassesAdapter (private val size: Int) :
    RecyclerView.Adapter<ScoresClassesAdapter.ViewHolder>() {

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

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position + 1)

    override fun getItemCount() = size

    class ViewHolder(val binding: ViewScoresClassesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(semester: Number) {


        }
    }
}
