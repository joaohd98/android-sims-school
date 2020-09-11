package screens.logged.tabs.tips.modal_medias.adapter

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasAdapter(
    fragmentActivity: FragmentActivity,
    private val tips: ArrayList<TipsResponse>,
    private val onChangeSlide: (Int) -> Unit
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() =  tips.size

    override fun createFragment(position: Int) =
        MediasItemFragment(tips[position], {
            if(position > 0) {
                onChangeSlide(position - 1)
            }
        }) {
            if (position < itemCount - 1) {
                onChangeSlide(position + 1)
            }
        }
}