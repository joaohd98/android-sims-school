package screens.logged.tabs.tips.modal_medias.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.tips.modal_medias.MediasViewModel

class MediasAdapter(
    private val fa: FragmentActivity,
    private var actualTipPosition: Int,
    private val size: Int,
    private val onDismiss: () -> Unit
) : FragmentStateAdapter(fa) {
    private val fragments: ArrayList<MediasItemFragment> = arrayListOf()

    override fun getItemCount() =  size

    override fun createFragment(position: Int): Fragment {
        val fragment = MediasItemFragment(position, onDismiss)

        fragments.add(fragment)

        return fragment
    }

    private fun hasFinishedLoading() = fragments.size > actualTipPosition

    fun changeActualTipPosition(position: Int) {
        actualTipPosition = position
    }

    fun changeHolding(isHolding: Boolean) {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                changeHolding(isHolding)
            }
        }
    }


    fun setText() {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                setText()
            }
        }
    }

}