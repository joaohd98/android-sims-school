package screens.logged.tabs.tips.modal_medias.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasAdapter(fa: FragmentActivity, private val tips: ArrayList<TipsResponse>) : FragmentStateAdapter(fa) {

    override fun getItemCount() =  tips.size

    override fun createFragment(position: Int) =
        MediasItemFragment(tips, position)
}