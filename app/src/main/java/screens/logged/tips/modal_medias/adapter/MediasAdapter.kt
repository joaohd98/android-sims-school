package screens.logged.tips.modal_medias.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import screens.logged.tips.modal_medias.MediasViewModel

class MediasAdapter(
    fa: FragmentActivity,
    private val viewModel: MediasViewModel,
    private var actualTipPosition: Int,
    private val size: Int,
    private val onDismiss: () -> Unit
) : FragmentStateAdapter(fa) {
    private val fragments: ArrayList<MediasItemFragment> = arrayListOf()

    override fun getItemCount() =  size

    override fun createFragment(position: Int): Fragment {
        val fragment = MediasItemFragment(position,  onDismiss)

        fragments.add(fragment)
        fragments.sortBy { it.position }
        
        return fragment
    }

    private fun hasFinishedLoading() = fragments.size > actualTipPosition

    fun changeActualTipPosition(position: Int) {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                isLastActiveTip()
            }
        }

        actualTipPosition = position

        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                isActiveTip()
            }
        }
    }

    fun changeHolding(isHolding: Boolean) {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                changeHolding(isHolding)
            }
        }
    }

    fun changedMedia() {
        if(hasFinishedLoading()) {
            Log.d("aaa", actualTipPosition.toString())

            fragments[actualTipPosition].apply {
                Log.d("aaa", viewModel.getActualTip(position).name)

                changedMedia()
            }
        }
    }

    fun changeSliding(isSliding: Boolean) {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                changeSliding(isSliding)
            }
        }
    }

    fun onLeavePage() {
        if(hasFinishedLoading()) {
            fragments.forEach {
                it.apply {
                    onLeavePage()
                }
            }
        }
    }

    fun onEnterBackground(isEntering: Boolean) {
        if(hasFinishedLoading()) {
            fragments[actualTipPosition].apply {
                onEnterBackground(isEntering)
            }
        }
    }
}