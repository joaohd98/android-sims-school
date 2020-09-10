package screens.logged.tabs.tips.modal_medias

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse

class MediasAdapter(
    private val tips: ArrayList<TipsResponse>,
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return tips.size
    }

    override fun createFragment(position: Int) = MediasItemFragment.newInstance(tips[position])
}