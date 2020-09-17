package screens.logged.tips.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.joao.simsschool.R
import com.joao.simsschool.databinding.ViewTipsListBinding
import repositories.tips.TipsResponse
import utils.CustomLayoutManager

class TipsListView: LinearLayout {
    lateinit var binding: ViewTipsListBinding

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        if (isInEditMode) {
            LayoutInflater.from(context).inflate(R.layout.view_tips_list, this, true)
        }
        else {
            binding = ViewTipsListBinding.inflate(LayoutInflater.from(context), this, true)
        }
    }

    fun initRecyclerView(fragmentManager: FragmentManager) {
        val viewManager = CustomLayoutManager(context, false)
        val viewAdapter = TipsListAdapter(fragmentManager)

        binding.viewTipsListRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun setLoading() {
        binding.viewTipsListRecyclerView.apply {
            (layoutManager as CustomLayoutManager).setScrollEnabled(false)
        }
    }

    fun setSuccess(tips: ArrayList<TipsResponse>) {
        binding.viewTipsListRecyclerView.apply {
            (layoutManager as CustomLayoutManager).setScrollEnabled(true)
            (adapter as TipsListAdapter).setSuccess(tips)
        }
    }

}