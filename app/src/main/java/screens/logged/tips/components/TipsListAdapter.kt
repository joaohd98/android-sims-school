package screens.logged.tips.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewTipsListItemBinding
import repositories.tips.TipsResponse
import kotlin.collections.ArrayList

class TipsListAdapter(
    private val fragmentManager: FragmentManager,
): RecyclerView.Adapter<TipsListAdapter.ViewHolder>() {

    private var isLoading = true
    private var size = 10

    override fun getItemCount() = size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil
            .inflate<ViewTipsListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.view_tips_list_item,
                parent,
                false
            )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    class ViewHolder(
        private val binding: ViewTipsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.viewTipsListItemCircleImage.clipToOutline = true
            binding.executePendingBindings()
        }
    }

}