package screens.logged.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import screens.logged.tabs.classes.ClassesScreen
import screens.logged.tabs.home.HomeScreen
import screens.logged.tabs.scores.ScoresScreen
import screens.logged.tabs.tips.TipsScreen

class TabsPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val pages = arrayOf(
        "Tips",
        "Classes",
        "Scores",
        "Home"
    )

    fun getTitle(position: Int): String {
        return pages[position]
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TipsScreen.newInstance()
            1 -> HomeScreen.newInstance()
            2 -> ScoresScreen.newInstance()
            3 -> ClassesScreen.newInstance()
            else -> { HomeScreen.newInstance() }
        }
    }
}
