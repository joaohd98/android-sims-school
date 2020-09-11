package screens.logged.tabs.tips.modal_medias.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse

class MediasAdapter(
    fa: FragmentActivity,
    private val tips: ArrayList<TipsResponse>,
    private val changeCurrent: (value: Int) -> Unit
) : FragmentStateAdapter(fa) {

    override fun getItemCount() =  tips.size

    override fun createFragment(position: Int) = MediasItemFragment.newInstance(tips[position], {}, {})
}