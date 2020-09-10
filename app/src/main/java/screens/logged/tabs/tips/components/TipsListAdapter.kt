package screens.logged.tabs.tips.components

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewTipsListItemBinding
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_all_tips.AllTipsModal
import utils.OnClickDataBinding
import utils.addSkeletonAllElementsInner
import utils.removeSkeletonAllElementsInner
import kotlin.collections.ArrayList

class TipsListAdapter(
    private val fragmentManager: FragmentManager,
): RecyclerView.Adapter<TipsListAdapter.ViewHolder>() {

    private lateinit var tips: ArrayList<TipsResponse>

    override fun getItemCount() = if(this::tips.isInitialized) tips.count() else 10

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
        if (this::tips.isInitialized) {
            holder.bind(tips[position]) {
                AllTipsModal.invoke(fragmentManager, tips, position)
            }
        }
    }

    class ViewHolder(
        private val binding: ViewTipsListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.viewTipsListItemInnerLinearLayout.addSkeletonAllElementsInner()
        }

        fun bind(response: TipsResponse, onClick: () -> Unit) {
            binding.response = response
            binding.viewTipsListItemInnerLinearLayout.removeSkeletonAllElementsInner()

            binding.onSelect = object: OnClickDataBinding() {
                override fun onClick() {
                    onClick()
                }
            }

            binding.executePendingBindings()
        }
    }

    fun setSuccess(tips: ArrayList<TipsResponse>) {
        this.tips = tips
        notifyDataSetChanged()
    }
}