package screens.logged.tabs

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import screens.logged.classes.ClassesScreen
import screens.logged.home.HomeScreen
import screens.logged.scores.ScoresScreen
import screens.logged.tips.TipsScreen

class TabsPageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    private val pages = arrayOf(
        "Home",
        "Scores",
        "Classes",
        "Tips",
    )

    fun getTitle(position: Int): String {
        return pages[position]
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> HomeScreen.newInstance()
            1 -> ScoresScreen.newInstance()
            2 -> ClassesScreen.newInstance()
            3 -> TipsScreen.newInstance()
            else -> { HomeScreen.newInstance() }
        }
    }
}
