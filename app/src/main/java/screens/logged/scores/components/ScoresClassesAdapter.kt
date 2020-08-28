package screens.logged.scores.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.databinding.ViewScoresClassesCardBinding

class ScoresClassesAdapter (private val size: Int) :
    RecyclerView.Adapter<ScoresClassesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewScoresClassesCardBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position + 1)

    override fun getItemCount() = size

    class ViewHolder(val binding: ViewScoresClassesCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(semester: Number) {
            binding.executePendingBindings()
        }
    }
}
