package screens.logged.tabs.tips.modal_medias

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.components.MediasItemFragment

class MediasAdapter(
    private val tips: ArrayList<TipsResponse>,
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return tips.size
    }

    override fun createFragment(position: Int): Fragment {
       return MediasItemFragment.newInstance(tips[position])
    }
}