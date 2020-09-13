package screens.logged.tabs.tips.modal_medias.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasAdapter(
    fa: FragmentActivity,
    private var actualTipIndex: Int,
    private val tips: ArrayList<TipsResponse>,
) : FragmentStateAdapter(fa) {
    private val fragments: ArrayList<MediasItemFragment> = arrayListOf()

    override fun getItemCount() =  tips.size

    override fun createFragment(position: Int): Fragment {
        val fragment = MediasItemFragment(position)

        fragments.add(fragment)

        return fragment
    }


    fun changeActualTipPosition(position: Int) {
        actualTipIndex = position
    }

    fun changeHolding(isHolding: Boolean) {
        fragments[actualTipIndex].apply {
            changeHolding(isHolding)
        }
    }

}