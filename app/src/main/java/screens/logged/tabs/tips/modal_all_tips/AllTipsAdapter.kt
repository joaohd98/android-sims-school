package screens.logged.tabs.tips.modal_all_tips

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import repositories.tips.TipsResponse
import screens.logged.tabs.classes.ClassesScreen
import screens.logged.tabs.home.HomeScreen
import screens.logged.tabs.scores.ScoresScreen
import screens.logged.tabs.tips.TipsScreen
import screens.logged.tabs.tips.modal_all_tips.components.AllTipsItem

class AllTipsAdapter(
    private val tips: ArrayList<TipsResponse>,
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return tips.size
    }

    override fun createFragment(position: Int): Fragment {
       return AllTipsItem.newInstance(tips[position])
    }
}