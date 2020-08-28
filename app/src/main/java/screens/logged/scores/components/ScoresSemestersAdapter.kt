package screens.logged.scores.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.databinding.ViewScoresSemestersCircleBinding

class ScoresSemestersAdapter(private val actualSemester: Int) :
    RecyclerView.Adapter<ScoresSemestersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewScoresSemestersCircleBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(position + 1)

    override fun getItemCount() = actualSemester

    class ViewHolder(val binding: ViewScoresSemestersCircleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(semester: Number) {
            binding.semester = semester.toString()
            binding.executePendingBindings()
        }
    }

}